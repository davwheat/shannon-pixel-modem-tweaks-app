package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.nrcapa

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class BwpNumerology : NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "Enable BWP Numerology IEs"
  @IgnoredOnParcel
  override val description = "Applies to both SIMs"

  override val nvItems: List<NvItem>
    get() =
        listOf(
            NvItem(id = "!NRCAPA.Band.BwpSameNumerology", value = "01"),
            NvItem(id = "!NRCAPA.Band.BwpSameNumerology_DS", value = "01"),
            NvItem(id = "!NRCAPA.Band.BwpDiffNumerology", value = "01"),
            NvItem(id = "!NRCAPA.Band.BwpDiffNumerology_DS", value = "01"),
        )
}
