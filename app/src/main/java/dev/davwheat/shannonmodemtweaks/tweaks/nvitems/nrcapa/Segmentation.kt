package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.nrcapa

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class Segmentation : NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "Enable RRC UL Segmentation"
  @IgnoredOnParcel
  override val description = "Applies to both SIMs"

  override val nvItems: List<NvItem>
    get() =
        listOf(
            NvItem(id = "!NRCAPA_SEGMENTATION_ALLOWED", value = "01"),
            NvItem(id = "!NRCAPA_SEGMENTATION_ALLOWED_DS", value = "01"),
            NvItem(id = "UECAPA_REL16_UL_RRC_SEGMENTATION_SUPPORT", value = "01"),
            NvItem(id = "UECAPA_REL16_UL_RRC_SEGMENTATION_SUPPORT", index = 1, value = "01"),
        )
}
