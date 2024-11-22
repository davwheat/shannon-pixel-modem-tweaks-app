package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa.filters

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class DisableReducedFormat: NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "Disable Reduced Format"
  @IgnoredOnParcel
  override val description = "Disable LTE Combinations Reduced Format"

  override val nvItems: List<NvItem>
    get() =
        listOf(
            NvItem(id = "UECAPA_REL13_REQUEST_REDUCED_FORMAT", value = "00"),
            NvItem(id = "UECAPA_REL13_REQUEST_REDUCED_FORMAT", index = 1, value = "00")
        )
}
