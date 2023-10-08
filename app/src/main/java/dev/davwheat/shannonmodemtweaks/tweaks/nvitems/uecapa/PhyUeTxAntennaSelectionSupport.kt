package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa

import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak

class PhyUeTxAntennaSelectionSupport : NvItemTweak() {
  override val name = "UE Tx antenna selection"
  override val description =
      "Select between antenna ports 0 and 1 when it supports multiple Tx antennas"

  override val nvItems: List<NvItem>
    get() = listOf(
      NvItem(id="UECAPA_PHY_UE_TX_ANTENNASEL_SUPPORT", value="01"),
    )
}
