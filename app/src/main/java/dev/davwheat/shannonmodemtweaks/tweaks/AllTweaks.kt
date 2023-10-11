package dev.davwheat.shannonmodemtweaks.tweaks

import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.AdditionalNrSaLocking
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NrCommHpUePc1Dot5SupportedBands
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.LteRrcLoggedMeas
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NrConfigMode
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.ims.EvsSwbHighBitrateSupport
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.nrcapa.SrsTxSwitch
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa.PhyUeSpecificRefSigSupport
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa.PhyUeTxAntennaSelectionSupport

val AllTweaks: Map<String, List<Tweak>> =
    mapOf(
        Pair(
            "Core Improvements",
            listOf(
                    PhyUeTxAntennaSelectionSupport(),
                    LteRrcLoggedMeas(),
                    NrConfigMode(),
                    PhyUeSpecificRefSigSupport(),
                    AdditionalNrSaLocking(),
                    SrsTxSwitch(),
                    NrCommHpUePc1Dot5SupportedBands(),
                )
                .sortedBy { it.name },
        ),
        Pair("IMS", listOf(EvsSwbHighBitrateSupport()).sortedBy { it.name }))
