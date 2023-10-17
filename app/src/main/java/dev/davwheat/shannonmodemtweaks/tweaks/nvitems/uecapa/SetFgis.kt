package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class SetLteFgis: NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "Enable various LTE FGIs"
  @IgnoredOnParcel
  override val description =
      "Marks various LTE feature group indicators as supported in the device's capabilities."

  // https://www.sharetechnote.com/html/Handbook_LTE_FGI.html
  private val items
      get() = listOf(
          "UECAPA_FGI31",
          "UECAPA_REL10_FGI41",
          "UECAPA_REL10_FGI101",
          "UECAPA_REL10_FGI102",
          "UECAPA_REL10_FGI103",
          "UECAPA_REL10_FGI104",
          "UECAPA_REL10_FGI105",
          "UECAPA_REL10_FGI106",
          "UECAPA_REL10_FGI107",
          "UECAPA_REL10_FGI108",
          "UECAPA_REL10_FGI109",
          "UECAPA_REL10_FGI110",
          "UECAPA_REL10_FGI111",
          "UECAPA_REL10_FGI112",
          "UECAPA_REL10_FGI113",
          "UECAPA_REL10_FGI114",
          "UECAPA_REL10_FGI115",
          "UECAPA_REL10_TM9_WITH_8TX",
          "UECAPA_XDD_FGI21",
          "UECAPA_XDD_FGI31",
          "UECAPA_XDD_REL10_FGI41",
          "UECAPA_XDD_REL10_FGI101",
          "UECAPA_XDD_REL10_FGI102",
          "UECAPA_XDD_REL10_FGI103",
          "UECAPA_XDD_REL10_FGI104",
          "UECAPA_XDD_REL10_FGI105",
          "UECAPA_XDD_REL10_FGI106",
          "UECAPA_XDD_REL10_FGI107",
          "UECAPA_XDD_REL10_FGI108",
          "UECAPA_XDD_REL10_FGI109",
          "UECAPA_XDD_REL10_FGI110",
          "UECAPA_XDD_REL10_FGI111",
          "UECAPA_XDD_REL10_FGI112",
          "UECAPA_XDD_REL10_FGI113",
          "UECAPA_XDD_REL10_FGI114",
          "UECAPA_XDD_REL10_FGI115",
      )

  override val nvItems: List<NvItem>
    get() =
        listOf(
            *items
                .map { id ->
                  NvItem(
                      id = id,
                      value = "01",
                  )
                }
                .toTypedArray(),
        )
}
