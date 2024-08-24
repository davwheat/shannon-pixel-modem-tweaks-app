package dev.davwheat.shannonmodemtweaks.tweaks.nvitems

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.utils.toNvItemHexString
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class NrCommHpUePc1Dot5SupportedBands : NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "Enable HPUE PC1.5"

  @IgnoredOnParcel
  override val description = "Enables HPUE power class 1.5 for n41/77"

  private val bands get() = listOf(41, 77)

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
          value = bands.size.toNvItemHexString(1),
        ),
      )
}
