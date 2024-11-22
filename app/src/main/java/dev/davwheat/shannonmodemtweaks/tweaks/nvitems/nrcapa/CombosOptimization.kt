package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.nrcapa

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class CombosOptimization : NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "NR Combos Optimization"

  @IgnoredOnParcel
  override val description = "Speeds up UE capability generation and compact them"

  override val nvItems: List<NvItem>
    get() =
      listOf(
        // 0x02 = Enable (8-9 series default), 0x00 = Disable (6-7 series default)
        NvItem(id = "OEM_GFEATURE_OPTI_COMBO_LEVEL", value = "02"),
        NvItem(id = "OEM_GFEATURE_OPTI_COMBO_LEVEL_DS", value = "02"),
      )
}
