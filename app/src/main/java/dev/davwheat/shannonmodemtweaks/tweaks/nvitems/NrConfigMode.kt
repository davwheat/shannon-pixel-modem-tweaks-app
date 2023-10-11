package dev.davwheat.shannonmodemtweaks.tweaks.nvitems

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
