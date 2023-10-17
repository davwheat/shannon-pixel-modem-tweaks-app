package dev.davwheat.shannonmodemtweaks.tweaks.nvitems

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class NrConfigMode: NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "Enable NSA and SA 5G modes"
  @IgnoredOnParcel
  override val description = "Applies to both SIMs"

  override val nvItems: List<NvItem>
    get() =
        listOf(
            // Sets allowed NR modes. 00=disabled, 01=NSA, 10=SA, 11=SA+NSA
            NvItem(id = "NR.CONFIG.MODE", value = "11"),
            NvItem(id = "DS.NR.CONFIG.MODE", value = "11"),
        )
}
