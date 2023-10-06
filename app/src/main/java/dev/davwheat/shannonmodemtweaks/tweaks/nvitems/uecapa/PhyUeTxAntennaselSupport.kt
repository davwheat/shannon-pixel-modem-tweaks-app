package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa

import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvitemTweak

class PhyUeTxAntennaselSupport : NvitemTweak() {
  override val name = "UE Tx antenna selection"
  override val description =
      "Select between antenna ports 0 and 1 when it supports multiple Tx antennas"

  override val nvitem = "UECAPA_PHY_UE_TX_ANTENNASEL_SUPPORT"
  override val nvitemValue = "01"
}
