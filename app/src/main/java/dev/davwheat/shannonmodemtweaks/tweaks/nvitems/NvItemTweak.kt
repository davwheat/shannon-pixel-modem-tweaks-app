package dev.davwheat.shannonmodemtweaks.tweaks.nvitems

import dev.davwheat.shannonmodemtweaks.tweaks.Tweak
import dev.davwheat.shannonmodemtweaks.tweaks.TweakType
import dev.davwheat.shannonmodemtweaks.utils.ExecuteAsRoot
import timber.log.Timber

class NvItem(val id: String, val value: String, val index: Int = 0) {
  init {
    require (!id.contains(regex = Regex("""[\\"]"""))) {
      "nvitem cannot contain backslashes or quotes"
    }
    require (index >= 0) {
      "nvitemIndex must be greater than or equal to 0"
    }
    require (!value.contains(regex = Regex("""[\\"]"""))) {
      "nvitemValue cannot contain backslashes or quotes"
    }
  }

  /**
   * Returns the AT command to apply this nvitem change.
   */
  override fun toString(): String {
    return "AT+GOOGSETNV=\"$id\",$index,\"$value\"\\r"
  }
}

abstract class NvItemTweak : Tweak() {
  override val type = TweakType.NVITEM

  abstract val nvItems: List<NvItem>

  override fun applyTweak(): Pair<Boolean, String> = applyNvItemChanges()

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
