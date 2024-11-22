package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa.filters

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class IgnoreSkipFallback: NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "Ignore Skip Fallback"
  @IgnoredOnParcel
  override val description = "Ignore requestSkipFallbackComb and requestDiffFallbackCombList"

  override val nvItems: List<NvItem>
    get() =
        listOf(
            NvItem(id = "UECAPA_REL13_REQUEST_SKIP_FALLBACKCOMB", value = "00"),
            NvItem(id = "UECAPA_REL14_DIFF_FALLBACKCOMB", value = "00"),

            NvItem(id = "UECAPA_REL13_REQUEST_SKIP_FALLBACKCOMB", index = 1, value = "00"),
            NvItem(id = "UECAPA_REL14_DIFF_FALLBACKCOMB", index = 1, value = "00")
        )
}
