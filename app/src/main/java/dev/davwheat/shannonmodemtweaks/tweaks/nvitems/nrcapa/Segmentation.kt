package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.nrcapa

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak
import dev.davwheat.shannonmodemtweaks.utils.InferDevice.PixelDevice
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
            NvItem(id = "UECAPA_REL16_UL_RRC_SEGMENTATION_SUPPORT", value = "01"),
            NvItem(id = "UECAPA_REL16_UL_RRC_SEGMENTATION_SUPPORT", index = 1, value = "01"),
            NvItem(id = "!NRRRC_R16_UL_RRC_SEGMENTATION", value = "01"),
            NvItem(id = "!NRRRC_R16_UL_RRC_SEGMENTATION_DS", value = "01"),
        )

  @IgnoredOnParcel
  private val compatibleDevices = setOf(
    PixelDevice.PIXEL_9,
    PixelDevice.PIXEL_9_PRO,
    PixelDevice.PIXEL_9_PRO_FOLD,
    PixelDevice.PIXEL_9_PRO_XL,
    PixelDevice.PIXEL_10,
    PixelDevice.PIXEL_10_PRO,
    PixelDevice.PIXEL_10_PRO_FOLD,
    PixelDevice.PIXEL_10_PRO_XL
  )

  override fun isTweakCompatible(device: PixelDevice): Boolean = device in compatibleDevices
}
