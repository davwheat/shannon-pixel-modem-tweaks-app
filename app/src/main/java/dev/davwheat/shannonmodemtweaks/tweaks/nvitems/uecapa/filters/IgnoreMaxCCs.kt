package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa.filters

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class IgnoreMaxCCs: NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "Ignore MAX CCs"
  @IgnoredOnParcel
  override val description = "Ignore requestedMaxCCsDL/UL"

  override val nvItems: List<NvItem>
    get() =
        listOf(
            NvItem(id = "UECAPA_REL13_MAXIMUM_CCS_RETRIEVAL", value = "00"),
            NvItem(id = "UECAPA_REL13_MAXIMUM_CCS_RETRIEVAL", index = 1, value = "00"),
        )
}
