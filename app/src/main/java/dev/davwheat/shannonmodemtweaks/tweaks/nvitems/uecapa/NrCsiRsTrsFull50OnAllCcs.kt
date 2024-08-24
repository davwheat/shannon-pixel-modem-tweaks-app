package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak
import dev.davwheat.shannonmodemtweaks.utils.toNvItemHexString
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class NrCsiRsTrsFull50OnAllCcs : NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "NR CSI-RS increase max TRS to 50"

  @IgnoredOnParcel
  override val description =
    "Enables up to 50 tracking signals in NR for CSI-RS (used to compensate for time/frequency drift)"

  override val nvItems
    get() = listOf(
      NvItem(
        id = "UECAPA_NR_RF_BAND_FR1_MIMO_CSIRS_TRACK_MAX_CONFIG_RSETS_ALLCC",
        value = 50.toNvItemHexString(2),
      ),
    )
}
