package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class PhyUeTxAntennaSelectionSupport : NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "UE Tx antenna selection"

  @IgnoredOnParcel
  override val description =
    "Select between antenna ports 0 and 1 when it supports multiple Tx antennas"

  override val nvItems: List<NvItem>
    get() =
      listOf(
        NvItem(id = "UECAPA_PHY_UE_TX_ANTENNASEL_SUPPORT", value = "01"),
      )
}
