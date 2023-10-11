package dev.davwheat.shannonmodemtweaks.tweaks.nvitems

class LteRrcLoggedMeas : NvItemTweak() {
  override val name = "UE signal measurement logging and reporting"
  override val description =
      "Allows the device to perform network-controlled cell signal measurements for analytics."

  override val nvItems: List<NvItem>
    get() =
        listOf(
            NvItem(id = "!LTEL3.LTERRC_LOGGED_MEAS", value = "01"),
        )
}
