package dev.davwheat.shannonmodemtweaks.tweaks.nvitems

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.utils.toNvItemHexString
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class NrMmwave: NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "Enable n257, n258, n260 and n261"
  @IgnoredOnParcel
  override val description = "Applies to both SIMs"

  override val nvItems: List<NvItem>
    get() =
        listOf(
            NvItem(id = "UECAPA_NR_RF_BAND_FR2_BM", value = 49242.toNvItemHexString(2)),
            NvItem(id = "UECAPA_NR_RF_BAND_21_FBI", value = 10257.toNvItemHexString(2)),
            NvItem(id = "UECAPA_NR_RF_BAND_22_FBI", value = 10258.toNvItemHexString(2)),
            NvItem(id = "UECAPA_NR_RF_BAND_23_FBI", value = 10260.toNvItemHexString(2)),
            NvItem(id = "UECAPA_NR_RF_BAND_24_FBI", value = 10261.toNvItemHexString(2)),
            NvItem(id = "UECAPA_NR_RF_BAND_FR2_BM_DS", value = 49242.toNvItemHexString(2)),
            NvItem(id = "UECAPA_NR_RF_BAND_21_FBI_DS", value = 10257.toNvItemHexString(2)),
            NvItem(id = "UECAPA_NR_RF_BAND_22_FBI_DS", value = 10258.toNvItemHexString(2)),
            NvItem(id = "UECAPA_NR_RF_BAND_23_FBI_DS", value = 10260.toNvItemHexString(2)),
            NvItem(id = "UECAPA_NR_RF_BAND_24_FBI_DS", value = 10261.toNvItemHexString(2)),
        )
}
