package dev.davwheat.shannonmodemtweaks.tweaks.nvitems

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.utils.toNvItemHexString
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class AdditionalNrSaLocking : NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "Enable additional NR SA locking"

  @IgnoredOnParcel
  override val description =
    "Adds n1/2/3/5/7/8/12/20/25/26/28/29/30/38/40/41/48/66/70/71/75/77/78/257/258/260/261 to supported NR SA bands"

  private val bands
    get() =
      listOf(1, 2, 3, 5, 7, 8, 12, 20, 25, 26, 28, 29, 30, 38, 40, 41, 48, 66, 70, 71, 75, 77, 78, 257, 258, 260, 261)

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
