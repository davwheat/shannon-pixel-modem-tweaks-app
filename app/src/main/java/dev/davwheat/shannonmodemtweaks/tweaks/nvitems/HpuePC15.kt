package dev.davwheat.shannonmodemtweaks.tweaks.nvitems

import dev.davwheat.shannonmodemtweaks.utils.toNvItemHexString

class HpuePC15 : NvItemTweak() {
  override val name = "Enable HPUE PC 1.5"
  override val description = "Enables HPUE Power Class 1.5 for supported bands"

  private val bands = listOf(41, 77)

  override val nvItems: List<NvItem>
    get() =
        listOf(
            *bands
                .mapIndexed { index, band ->
                  NvItem(
                      id = "!NRCOMM_PC1DOT5_SUPPORTED_BANDS",
                      index = index,
                      value = band.toNvItemHexString(2),
                  )
                }
                .toTypedArray(),

            // Bands count
            NvItem(
                id = "!NRCOMM_PC1DOT5_SUPPORTED_BANDS_NUM",
                value = bands.size.toNvItemHexString(2),
            ),
        )
}
