package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.nrcapa

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class Rrcinactive : NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "Enable RRC Inactive state support"
  @IgnoredOnParcel
  override val description = "Applies to both SIMs"

  override val nvItems: List<NvItem>
    get() =
        listOf(
            NvItem(id = "!NRCAPA_INACTIVE_STATE", value = "01"),
            NvItem(id = "!NRCAPA_INACTIVE_STATE_DS", value = "01"),
        )
}
