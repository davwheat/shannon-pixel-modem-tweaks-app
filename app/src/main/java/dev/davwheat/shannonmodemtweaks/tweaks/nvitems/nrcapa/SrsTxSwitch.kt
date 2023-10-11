package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.nrcapa

import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak

class SrsTxSwitch : NvItemTweak() {
  override val name = "SRS Tx antenna switching"
  override val description =
      "Device can transmit reference signals on Rx antenna chain to improve DL performance."

  override val nvItems: List<NvItem>
    get() =
        listOf(
            NvItem(id = "!NRCAPA.Gen.SrsTxSwitch", value = "01"),
        )
}
