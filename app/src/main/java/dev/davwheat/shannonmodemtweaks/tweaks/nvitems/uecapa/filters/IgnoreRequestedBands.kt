package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa.filters

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class IgnoreRequestedBands: NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "Ignore requested bands"
  @IgnoredOnParcel
  override val description = "Ignore requestedFrequencyBands and requestedFreqBandsNR-MRDC (for eutra-nr/nr as well)"

  override val nvItems: List<NvItem>
    get() =
        listOf(
            NvItem(id = "UECAPA_REL11_FREQ_BAND_RETRIEVAL", value = "00"),
            NvItem(id = "UECAPA_REL11_FREQ_BAND_RETRIEVAL", index = 1, value = "00"),
        )
}
