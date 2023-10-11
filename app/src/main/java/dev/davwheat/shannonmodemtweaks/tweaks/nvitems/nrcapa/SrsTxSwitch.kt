package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.nrcapa

import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak

class SrsTxSwitch : NvItemTweak() {
  override val name = "SRS Tx antenna switching"
  override val description =
      "Allows the device to perform network-controlled cell signal measurements for analytics."

  override val nvItems: List<NvItem>
    get() =
        listOf(
            NvItem(id = "!LTEL3.LTERRC_LOGGED_MEAS", value = "01"),
        )
}
