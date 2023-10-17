package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.nrcapa

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class SrsTxSwitch : NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "SRS Tx antenna switching"
  @IgnoredOnParcel
  override val description =
      "Device can transmit reference signals on Rx antenna chain to improve DL performance."

  override val nvItems: List<NvItem>
    get() =
        listOf(
            NvItem(id = "!NRCAPA.Gen.SrsTxSwitch", value = "01"),
        )
}
