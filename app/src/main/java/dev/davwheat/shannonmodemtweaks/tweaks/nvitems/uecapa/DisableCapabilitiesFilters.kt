package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class DisableCapabilitiesFilters: NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "Disable EUTRA Capabilities filters"
  @IgnoredOnParcel
  override val description = "Useful to get all supported bands"

  override val nvItems: List<NvItem>
    get() =
        listOf(
            NvItem(id = "UECAPA_REL11_FREQ_BAND_RETRIEVAL", value = "00"),
            NvItem(id = "UECAPA_REL13_MAXIMUM_CCS_RETRIEVAL", value = "00"),
            NvItem(id = "UECAPA_REL13_REQUEST_REDUCED_FORMAT", value = "00"),
            NvItem(id = "UECAPA_REL13_REQUEST_SKIP_FALLBACKCOMB", value = "00"),
            NvItem(id = "UECAPA_REL14_DIFF_FALLBACKCOMB", value = "00"),

            NvItem(id = "UECAPA_REL11_FREQ_BAND_RETRIEVAL", index = 1, value = "00"),
            NvItem(id = "UECAPA_REL13_MAXIMUM_CCS_RETRIEVAL", index = 1, value = "00"),
            NvItem(id = "UECAPA_REL13_REQUEST_REDUCED_FORMAT", index = 1, value = "00"),
            NvItem(id = "UECAPA_REL13_REQUEST_SKIP_FALLBACKCOMB", index = 1, value = "00"),
            NvItem(id = "UECAPA_REL14_DIFF_FALLBACKCOMB", index = 1, value = "00")
        )
}
