package dev.davwheat.shannonmodemtweaks.utils

import timber.log.Timber

object IsNsgRunning {
  fun isNsgRunning(): Boolean? {
    val out =
        ExecuteAsRoot.executeAsRoot(listOf("ps -A | grep com\\.qtrun\\.QuickTest")) ?: return null

    if (out.isEmpty()) {
      return null
    }

    Timber.d(
        "Is NSG running? Ret code ${out[0]?.first} ${out[0]?.second?.contains("com.qtrun.QuickTest")}")

    return out[0]?.second?.contains("com.qtrun.QuickTest")
  }
}
