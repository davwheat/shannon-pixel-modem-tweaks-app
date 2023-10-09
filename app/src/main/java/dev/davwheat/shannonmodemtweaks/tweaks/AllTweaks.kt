package dev.davwheat.shannonmodemtweaks.tweaks

import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.AdditionalNrSaLocking
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.LteRrcLoggedMeas
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NrConfigMode
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa.PhyUeSpecificRefSigSupport
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa.PhyUeTxAntennaSelectionSupport
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.HpuePC15

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
		HpuePC15(),
            ).sortedBy { it.name },
        ),
    )
