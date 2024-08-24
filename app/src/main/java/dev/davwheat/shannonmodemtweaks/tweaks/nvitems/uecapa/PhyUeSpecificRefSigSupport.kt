package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class PhyUeSpecificRefSigSupport : NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "TM7-9 UE specific reference signal support"

  @IgnoredOnParcel
  override val description =
    "Allows UE to interpret specific reference signals in TM7-9 for DL beamforming"

  override val nvItems: List<NvItem>
    get() =
      listOf(
        NvItem(id = "UECAPA_PHY_UE_SPECIFIC_REF_SIG_SUPPORT", value = "01"),
      )
}
