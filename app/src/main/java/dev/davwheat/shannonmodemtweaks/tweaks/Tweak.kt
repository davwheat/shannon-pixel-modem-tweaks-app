package dev.davwheat.shannonmodemtweaks.tweaks

abstract class Tweak {
  abstract val type: TweakType
  abstract val name: String
  abstract val description: String

  abstract fun applyTweak(): Pair<Boolean, String>
}

enum class TweakType {
  NVITEM,
}
