package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak
import dev.davwheat.shannonmodemtweaks.utils.InferDevice.PixelDevice
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class setUeCat : NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "Max UE Cat"

  @IgnoredOnParcel
  override val description = "Sets maximum UE and DL, UL Cat for LTE ULCA"

  override val nvItems: List<NvItem>
    get() =
      listOf(
        NvItem(id = "UECAPA_UE_CATEGORY", value = "0C"),
        NvItem(id = "UECAPA_UE_CATEGORY", index = 1, value = "0C"),
        NvItem(id = "UECAPA_REL12_CATEGORY_DL", value = "14"),
        NvItem(id = "UECAPA_REL12_CATEGORY_DL", index = 1, value = "14"),
        NvItem(id = "UECAPA_REL12_CATEGORY_UL", value = "12"),
        NvItem(id = "UECAPA_REL12_CATEGORY_UL", index = 1, value = "12")
      )
      
  @IgnoredOnParcel
  private val compatibleDevices = setOf(
    PixelDevice.PIXEL_9,
    PixelDevice.PIXEL_9_PRO,
    PixelDevice.PIXEL_9_PRO_FOLD,
    PixelDevice.PIXEL_9_PRO_XL
  )
  override fun isTweakCompatible(device: PixelDevice): Boolean = device in compatibleDevices
}
