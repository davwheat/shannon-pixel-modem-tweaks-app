package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa

import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak

class Qam256UploadSupport : NvItemTweak() {
  override val name = "256QAM upload support"
  override val description = "Enables 256QAM upload on LTE and NR"

  override val nvItems: List<NvItem>
    get() =
        listOf(
            NvItem(id = "UECAPA_REL14_UL_256QAM_SUPPORT", value = "01"),
            NvItem(id = "UECAPA_REL15_FSULPCC_1_256Q", value = "01"),
            NvItem(id = "UECAPA_REL15_FSULPCC_2_256Q", value = "01"),
        )
}
