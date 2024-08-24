package dev.davwheat.shannonmodemtweaks.tweaks

import dev.davwheat.shannonmodemtweaks.utils.InferDevice.PixelDevice

abstract class Tweak {
  abstract val type: TweakType
  abstract val name: String
  abstract val description: String
  abstract val canBeDisabled: Boolean

  abstract suspend fun isTweakEnabled(): Boolean

  abstract fun applyTweak(): Pair<Boolean, String>

  private val compatibleDevices: Set<PixelDevice> = PixelDevice.entries.toSet()
  open fun isTweakCompatible(device: PixelDevice): Boolean = device in compatibleDevices
}

enum class TweakType {
  NVITEM,
}
