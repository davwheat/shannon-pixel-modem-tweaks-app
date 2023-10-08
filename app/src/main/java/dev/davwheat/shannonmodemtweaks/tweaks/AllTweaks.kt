package dev.davwheat.shannonmodemtweaks.tweaks

import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa.LteRrcLoggedMeas
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa.NrConfigMode
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
            ).sortedBy { it.name },
        ),
    )
