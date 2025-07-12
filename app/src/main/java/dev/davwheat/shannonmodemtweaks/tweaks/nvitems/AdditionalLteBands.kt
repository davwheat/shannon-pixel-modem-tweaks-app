package dev.davwheat.shannonmodemtweaks.tweaks.nvitems

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class AdditionalLteBands : NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "Additional LTE bands"

  @IgnoredOnParcel
  override val description = "Enable hw supported LTE bands"

  override val nvItems: List<NvItem>
    get() =
      listOf(
        NvItem(id = "!SAEL3.UPDATE_MCC_BAND_ENABLE", value = "00"),
        NvItem(id = "!SAEL3.ALL_RF_BAND_SUPPORT", value = "01"),
        NvItem(id = "OEM_GFEATURE_ENABLE_FCC_BANDS", value = "00"),
        NvItem(id = "OEM_GFEATURE_ENABLE_FCC_BANDS_DS", value = "00")
      )
}
