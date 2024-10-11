package dev.davwheat.shannonmodemtweaks.tweaks.nvitems

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class Rel16: NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "This set uecap release to rel 16"
  @IgnoredOnParcel
  override val description = "Applies to both NR and LTE"

  override val nvItems: List<NvItem>
    get() =
        listOf(
            // for LTE 08 = rel 16, for NR 01 = rel 16
            NvItem(id = "UECAPA_AS_RELEASE", value = "08"),
            NvItem(id = "!NRRRC_INSERT_TEST_AS_RELEASE_VER", value = "01,00,00,00"),
            NvItem(id = "UECAPA_AS_RELEASE", index = 1, value = "08"),
            NvItem(id = "!NRRRC_INSERT_TEST_AS_RELEASE_VER_DS", value = "01,00,00,00")
        )
}
