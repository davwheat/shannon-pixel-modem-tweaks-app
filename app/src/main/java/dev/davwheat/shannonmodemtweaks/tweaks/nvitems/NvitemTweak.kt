package dev.davwheat.shannonmodemtweaks.tweaks.nvitems

import dev.davwheat.shannonmodemtweaks.tweaks.Tweak
import dev.davwheat.shannonmodemtweaks.tweaks.TweakType
import dev.davwheat.shannonmodemtweaks.utils.ExecuteAsRoot
import timber.log.Timber

abstract class NvitemTweak : Tweak() {
  override val type = TweakType.NVITEM

  abstract val nvitem: String
  @Suppress("MemberVisibilityCanBePrivate") protected val nvitemIndex: Int = 0
  abstract val nvitemValue: String

  override fun applyTweak(): Boolean = applyNvitemChange()

  private fun buildAtCommand(): String {
    if (nvitem.contains(regex = Regex("""[\\"]"""))) {
      throw IllegalArgumentException("nvitem cannot contain backslashes or quotes")
    }
    if (nvitemValue.contains(regex = Regex("""[\\"]"""))) {
      throw IllegalArgumentException("nvitemValue cannot contain backslashes or quotes")
    }

    return "AT+GOOGSETNV=\"$nvitem\",$nvitemIndex,\"$nvitemValue\"\\r"
  }

  private fun buildShellCommand(): String {
    return "echo '${buildAtCommand()}' > /dev/umts_router & cat /dev/umts_router"
  }

  private fun applyNvitemChange(): Boolean {
    val cmd = buildShellCommand()

    Timber.d("Executing command: $cmd")
    val result = ExecuteAsRoot.executeAsRoot(listOf(cmd)) ?: return false

    val (_, output) = result[0] ?: return false
    Timber.d("Result: $output")

    return output.lowercase().contains("ok")
  }
}
