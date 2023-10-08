package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa

import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak

class NrConfigMode : NvItemTweak() {
  override val name = "NR config mode"
  override val description = "Enable NR NSA and SA modes."

  override val nvItems: List<NvItem>
    get() =
        listOf(
            NvItem(id = "NR.CONFIG.MODE", value = "11"),
            NvItem(id = "DS.NR.CONFIG.MODE", value = "11"),
        )
}
