package dev.davwheat.shannonmodemtweaks.tweaks.nvitems

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class LteRrcLoggedMeas: NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "UE signal measurement logging and reporting"
  @IgnoredOnParcel
  override val description =
      "Allows the device to perform network-controlled cell signal measurements for analytics."

  override val nvItems: List<NvItem>
    get() =
        listOf(
            NvItem(id = "!LTEL3.LTERRC_LOGGED_MEAS", value = "01"),
        )
}
