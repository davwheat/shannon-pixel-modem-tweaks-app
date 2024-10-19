package dev.davwheat.shannonmodemtweaks.tweaks.nvitems

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class Rel17: NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "Set NR release to rel 17"
  @IgnoredOnParcel
  override val description = "Enable rel 17 version"

  override val nvItems: List<NvItem>
    get() =
        listOf(
            // NR 02 = Rel 17
            NvItem(id = "!NRRRC_INSERT_TEST_AS_RELEASE_VER", value = "02,00,00,00"),
            NvItem(id = "!NRRRC_INSERT_TEST_AS_RELEASE_VER_DS", value = "02,00,00,00")
        )
}
