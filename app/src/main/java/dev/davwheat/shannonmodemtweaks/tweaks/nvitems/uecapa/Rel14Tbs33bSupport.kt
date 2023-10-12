package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa

import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak

class Rel14Tbs33bSupport : NvItemTweak() {
  override val name = "TBS 33B support for 256QAM LTE"
  override val description = "Enable TBS index 33B for 256QAM for ~3% DL performance uplift"

  override val nvItems: List<NvItem>
    get() = listOf(NvItem(id = "UECAPA_REL14_ALTERNATIVE_TBS_INDEX_R14_SUPPORT", value = "01"))
}
