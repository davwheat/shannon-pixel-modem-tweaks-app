package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa.rel14

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class volteRecBitRateR14 : NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "VoLTE recommended bitrate support R14"

  @IgnoredOnParcel
  override val description = "Enable VoLTE recommended bitrate support R14"

  override val nvItems: List<NvItem>
    get() =
      listOf(
        NvItem(id = "UECAPA_REL14_RECOMM_BIT_RATE", index = 0, value = "01"),
        NvItem(id = "UECAPA_REL14_RECOMM_BIT_RATE", index = 1, value = "01"),
        NvItem(id = "UECAPA_REL14_RECOMM_BIT_RATE_QUERY", index = 0, value = "01"),
        NvItem(id = "UECAPA_REL14_RECOMM_BIT_RATE_QUERY", index = 1, value = "01")
      )
}
