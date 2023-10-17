package dev.davwheat.shannonmodemtweaks.tweaks.nvitems.ims

import android.os.Parcelable
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class EvsSwbHighBitrateSupport : NvItemTweak(), Parcelable {
  @IgnoredOnParcel
  override val name = "Enhanced IMS call quality"
  @IgnoredOnParcel
  override val description = "Enables high bitrate EVS-SWB IMS calls."

  override val nvItems: List<NvItem>
    get() =
        listOf(
            NvItem(id = "PSS.AIMS.Operator-Spec", value = "A9"),
            NvItem(id = "PSS.AIMS.Audio.Capability", value = "05"),
            NvItem(id = "PSS.AIMS.Payload-EVS", value = "7F"),
            NvItem(id = "PSS.AIMS.Payload-EVS", index = 1, value = "7E"),
            NvItem(id = "PSS.AIMS.EVS.BW.Info", value = "03"),
            NvItem(id = "PSS.AIMS.EVS.BW.Info", index = 1, value = "02"),
            NvItem(id = "PSS.AIMS.EVS.BR.Min", value = "04"),
            NvItem(id = "PSS.AIMS.EVS.BR.Min", index = 1, value = "01"),
            NvItem(id = "PSS.AIMS.EVS.BR.Max", value = "07"),
            NvItem(id = "PSS.AIMS.EVS.BR.Max", index = 1, value = "07"),
            NvItem(id = "PSS.AIMS.EVS.ChAwRecv", value = "02"),
            NvItem(id = "PSS.AIMS.EVS.ChAwRecv", index = 1, value = "02"),
            NvItem(id = "PSS.AIMS.EVS.Codec.Info", value = "02"),
            NvItem(id = "PSS.AIMS.EVS.Codec.Info", index = 1, value = "0F"),
            NvItem(id = "PSS.AIMS.EVS.Codec.Info", index = 2, value = "0F"),
            NvItem(id = "PSS.AIMS.EVS.Codec.Info", index = 3, value = "0F"),
            NvItem(id = "PSS.AIMS.EVS.Codec.Info", index = 4, value = "01"),
            NvItem(id = "PSS.AIMS.EVS.Codec.Info", index = 5, value = "01"),
            NvItem(id = "PSS.AIMS.EVS.Codec.Info", index = 6, value = "00"),
            NvItem(id = "PSS.AIMS.EVS.Codec.Info", index = 7, value = "04"),
            NvItem(id = "PSS.AIMS.EVS.Codec.Info", index = 8, value = "07"),
            NvItem(id = "PSS.AIMS.EVS.Codec.Info", index = 9, value = "00"),
            NvItem(id = "PSS.AIMS.EVS.Codec.Info", index = 10, value = "00"),
            NvItem(id = "PSS.AIMS.EVS.Codec.Info", index = 11, value = "00"),
            NvItem(id = "PSS.AIMS.EVS.Codec.Info", index = 12, value = "00"),
            NvItem(id = "PSS.AIMS.EVS.Codec.Info", index = 13, value = "06"),
            NvItem(id = "PSS.AIMS.EVS.Codec.Info", index = 14, value = "00"),
            NvItem(id = "PSS.AIMS.EVS.Codec.Info", index = 15, value = "00"),
            NvItem(id = "PSS.AIMS.EVS.Codec.Info", index = 16, value = "01"),
            NvItem(id = "PSS.AIMS.EVS.Codec.Info", index = 17, value = "01"),
            NvItem(id = "PSS.AIMS.EVS.Codec.Info", index = 18, value = "02"),
            NvItem(id = "PSS.AIMS.EVS.Codec.Info", index = 19, value = "00"),
            NvItem(id = "PSS.AIMS.EVS.Codec.Info", index = 20, value = "02"),
            NvItem(id = "PSS.AIMS.EVS.Codec.Info", index = 21, value = "00"),
        )
}
