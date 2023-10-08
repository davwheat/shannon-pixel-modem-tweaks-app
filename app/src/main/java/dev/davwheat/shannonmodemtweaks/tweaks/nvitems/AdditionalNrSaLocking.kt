package dev.davwheat.shannonmodemtweaks.tweaks.nvitems

import dev.davwheat.shannonmodemtweaks.utils.toNvItemHexString

class AdditionalNrSaLocking : NvItemTweak() {
  override val name = "Enable additional NR SA locking"
  override val description = "Adds n1/3/7/8/20/28/38/40/41/66/71/77/78 to supported NR SA bands"

  private val bands = listOf(1, 3, 7, 8, 20, 28, 38, 40, 41, 66, 71, 77, 78)

  override val nvItems: List<NvItem>
    get() =
        listOf(
            *bands
                .mapIndexed { index, band ->
                  NvItem(
                      id = "!NRRRC.SUPPORTED_NR_BAND_LIST",
                      index = index,
                      value = band.toNvItemHexString(2),
                  )
                }
                .toTypedArray(),

            // Bands count
            NvItem(
                id = "!NRRRC.NUM_SUPPORTED_NR_BAND_LIST",
                value = bands.size.toNvItemHexString(2),
            ),
        )
}
