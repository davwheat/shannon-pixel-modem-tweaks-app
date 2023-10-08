package dev.davwheat.shannonmodemtweaks.tweaks.nvitems

import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak

class NrConfigMode : NvItemTweak() {
  override val name = "Enable NSA and SA 5G modes"
  override val description = "Applies to both SIMs"

  override val nvItems: List<NvItem>
    get() =
        listOf(
            // Sets allowed NR modes. 00=disabled, 01=NSA, 10=SA, 11=SA+NSA
            NvItem(id = "NR.CONFIG.MODE", value = "11"),
            NvItem(id = "DS.NR.CONFIG.MODE", value = "11"),
        )
}
