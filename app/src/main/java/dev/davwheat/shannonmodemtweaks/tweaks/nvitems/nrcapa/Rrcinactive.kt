package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.nrcapa

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak
import dev.davwheat.shannonmodemtweaks.utils.InferDevice.PixelDevice
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class Rrcinactive : NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "Enable RRC Inactive state support"
  @IgnoredOnParcel
  override val description = "Applies to both SIMs"

  override val nvItems: List<NvItem>
    get() =
        listOf(
            NvItem(id = "!NRCAPA_INACTIVE_STATE", value = "01"),
            NvItem(id = "!NRCAPA_INACTIVE_STATE_DS", value = "01"),
        )

  @IgnoredOnParcel
  private val compatibleDevices = setOf(
    PixelDevice.PIXEL_9,
    PixelDevice.PIXEL_9_PRO,
    PixelDevice.PIXEL_9_PRO_FOLD,
    PixelDevice.PIXEL_9_PRO_XL,
    PixelDevice.PIXEL_9A,
  )

  override fun isTweakCompatible(device: PixelDevice): Boolean = device in compatibleDevices
}
