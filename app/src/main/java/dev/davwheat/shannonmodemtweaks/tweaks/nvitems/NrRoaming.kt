package dev.davwheat.shannonmodemtweaks.tweaks.nvitems

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class NrRoaming: NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "Enable 5G NSA and SA when roaming"
  @IgnoredOnParcel
  override val description = "Applies to both SIMs"

  override val nvItems: List<NvItem>
    get() =
        listOf(
            // 0F = disables both SA and NSA, 0A = disables SA, 08 = disables neither
            NvItem(id = "PSS.GMC_NrRoamingDisableType", value = "08"),
            NvItem(id = "DS.PSS.GMC_NrRoamingDisableType", value = "08"),
        )
}
