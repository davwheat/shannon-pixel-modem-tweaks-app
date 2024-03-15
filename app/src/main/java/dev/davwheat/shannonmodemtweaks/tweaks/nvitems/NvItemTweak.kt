package dev.davwheat.shannonmodemtweaks.tweaks.nvitems

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.tweaks.Tweak
import dev.davwheat.shannonmodemtweaks.tweaks.TweakType
import dev.davwheat.shannonmodemtweaks.utils.ExecuteAsRoot
import kotlinx.parcelize.Parcelize
import timber.log.Timber

@Parcelize
class NvItem(val id: String, val value: String, val index: Int = 0): Parcelable {
  init {
    require(!id.contains(regex = Regex("""[\\"]"""))) {
      "nvitem cannot contain backslashes or quotes"
    }
    require(index >= 0) { "nvitemIndex must be greater than or equal to 0" }
    require(!value.contains(regex = Regex("""[\\"]"""))) {
      "nvitemValue cannot contain backslashes or quotes"
    }
  }

  /** Returns the AT command to apply this nvitem change. */
  override fun toString(): String {
    return "AT+GOOGSETNV=\"$id\",$index,\"$value\"\\r"
  }
}

abstract class NvItemTweak : Tweak() {
  override val type = TweakType.NVITEM

  /** NvItem tweaks are permanent, unless the user resets their EFS. */
  override val canBeDisabled = false

  abstract val nvItems: List<NvItem>

  /** Cached value of whether the tweak is enabled or not. */
  private var isEnabled: Boolean? = null

  override fun applyTweak(): Pair<Boolean, String> {
    val output = applyNvItemChanges()
    isEnabled = null
    return output
  }

  private fun parseGetNvItemOutput(output: String): List<NvItem> {
    val nvItems = mutableListOf<NvItem>()

    val lines = output.split("\n")
    for (l in lines) {
      val line = l.trim()

      if (!line.startsWith("+GOOGGETNV: ")) {
        continue
      }

      val nvItem = line.removePrefix("+GOOGGETNV: ").split(",")

      if (nvItem.size != 3) {
        continue
      }

      val id = nvItem[0].removeSurrounding("\"")
      val index = nvItem[1].toInt()
      val value = nvItem[2].removeSurrounding("\"")

      Timber.d("Found nvitem: $id, $index, $value")

      try {
        nvItems.add(NvItem(id, value, index))
      } catch (e: IllegalArgumentException) {
        Timber.e(e)
      }
    }

    return nvItems
  }

  override suspend fun isTweakEnabled(): Boolean {
    // Map of IDs to NvItem instances
    val setNvItems = mutableMapOf<String, List<NvItem>>()

    if (isEnabled != null) {
      return isEnabled!!
    }

    isEnabled =
        nvItems.all {
          var attempts = 0

          while (attempts < 3) {
            // If we don't have the result cached, fetch it from the modem
            if (!setNvItems.containsKey(it.id)) {
              // Execute GETNV command
              val cmd =
                  "echo 'AT+GOOGGETNV=\"${it.id}\"\\r' > /dev/umts_router & cat /dev/umts_router"

              Timber.d("Executing command: $cmd")
              val result = ExecuteAsRoot.executeAsRoot(listOf(cmd)) ?: return@all false

              val (_, output) = result[0] ?: return false
              Timber.d(output)
              val wasOk = output.lowercase().contains("ok")

              val nvItems = parseGetNvItemOutput(output)

              if (!wasOk) {
                // Sometimes the modem doesn't respond to the command, so we retry
                Timber.d("No nvitems found, retrying...")
                ++attempts
                Thread.sleep((attempts * 500).toLong())
                continue
              }

              setNvItems[it.id] = nvItems
            }

            break
          }

          val out =
              setNvItems[it.id]?.any { nv ->
                Timber.d(
                    "Comparing ${nv.id}: ${nv.index}, ${nv.value} to ${it.index}, ${it.value}")

                it.index == nv.index && it.value == nv.value
              } ?: false

          if (!out) {
            Timber.d("nvitem '${it.id}',${it.index} does not have value ${it.value}")
          }

          out
        }

    if (!isEnabled!!) {
      Timber.d("nvitem tweak '${name}' is NOT ENABLED")
    } else {
      Timber.d("nvitem tweak '${name}' is ENABLED")
    }

    Timber.d("=============================================================")

    return isEnabled!!
  }

  private fun buildAtCommands(): List<String> {
    return nvItems.map { nvItem ->
      if (nvItem.id.contains(regex = Regex("""[\\"]"""))) {
        throw IllegalArgumentException("nvitem cannot contain backslashes or quotes")
      }
      if (nvItem.value.contains(regex = Regex("""[\\"]"""))) {
        throw IllegalArgumentException("nvitemValue cannot contain backslashes or quotes")
      }

      nvItem.toString()
    }
  }

  private fun buildShellCommand(): String {
    return "echo '${buildAtCommands().joinToString("\n")}' > /dev/umts_router & cat /dev/umts_router"
  }

  private fun applyNvItemChanges(): Pair<Boolean, String> {
    val cmd = buildShellCommand()

    Timber.d("Executing command: $cmd")
    val result =
        ExecuteAsRoot.executeAsRoot(listOf(cmd)) ?: return Pair(false, "Failed to execute command")

    val (_, output) = result[0] ?: return Pair(false, "**No output**")
    Timber.d("Result: $output")

    return Pair(output.lowercase().contains("ok"), output)
  }
}
