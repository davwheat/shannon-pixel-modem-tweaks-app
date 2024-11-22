package dev.davwheat.shannonmodemtweaks.tweaks

import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.AdditionalNrSaLocking
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.LteRrcLoggedMeas
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NrCommHpUePc1Dot5SupportedBands
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NrConfigMode
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NrMmwave
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NrRoaming
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.Rel16
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.Rel17
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.ims.EvsSwbHighBitrateSupport
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.nrcapa.CombosOptimization
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.nrcapa.Segmentation
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.nrcapa.SrsTxSwitch
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.nrcapa.Rrcinactive
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa.filters.IgnoreMaxCCs
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa.NrCsiRsTrsFull50OnAllCcs
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa.PhyUeSpecificRefSigSupport
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa.PhyUeTxAntennaSelectionSupport
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa.Qam256UploadSupport
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa.SetLteFgis
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa.filters.DisableReducedFormat
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa.filters.IgnoreRequestedBands
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa.filters.IgnoreSkipFallback
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.uecapa.rel14.Tbs33bSupport

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
        Qam256UploadSupport(),
      )
        .sortedBy { it.name },
    ),
    Pair(
      "IMS",
      listOf(
        EvsSwbHighBitrateSupport(),
      )
        .sortedBy { it.name },
    ),
    Pair(
      "Release 14",
      listOf(
        Tbs33bSupport(),
      )
        .sortedBy { it.name },
    ),
    Pair(
      "Feature group indicators",
      listOf(
        SetLteFgis(),
      )
        .sortedBy { it.name },
    ),
    Pair(
      "NR only",
      listOf(
        NrRoaming(),
        NrMmwave(),
        NrCsiRsTrsFull50OnAllCcs(),
      )
        .sortedBy { it.name },
    ),
    Pair(
      "LTE Capabilities filters",
      listOf(
        IgnoreRequestedBands(),
        IgnoreMaxCCs(),
        DisableReducedFormat(),
        IgnoreSkipFallback(),
      )
        .sortedBy { it.name },
    ),
    Pair(
      "Advanced",
      listOf(
        Segmentation(),
        Rel16(),
        Rel17(),
        Rrcinactive(),
        CombosOptimization(),
      )
        .sortedBy { it.name },
    )
  )
