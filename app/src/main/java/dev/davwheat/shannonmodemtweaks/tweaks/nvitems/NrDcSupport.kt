package dev.davwheat.shannonmodemtweaks.tweaks.nvitems

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class NrDcSupport : NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "NR-DC Support"

  @IgnoredOnParcel
  override val description = "Enable NR-DC Support Dual-SIM"

  override val nvItems: List<NvItem>
    get() =
      listOf(
        NvItem(id = "!NRCOMMON.NrDcSupport", value = "01"),
        NvItem(id = "!NRCOMMON.NrDcSupport_DS", value = "01")
      )
}
