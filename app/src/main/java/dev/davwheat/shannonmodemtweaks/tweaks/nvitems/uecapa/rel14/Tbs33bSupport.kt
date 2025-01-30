package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa.rel14

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class Tbs33bSupport : NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "TBS 33B support for 256QAM LTE"

  @IgnoredOnParcel
  override val description = "Enable TBS index 33B for 256QAM for ~3% DL performance uplift"

  override val nvItems: List<NvItem>
    get() =
      listOf(
        NvItem(id = "UECAPA_REL14_ALTERNATIVE_TBS_INDEX_R14_SUPPORT", value = "01"),
        NvItem(id = "UECAPA_REL14_ALTERNATIVE_TBS_INDEX_R14_SUPPORT", index = 1, value = "01")
      )
}
