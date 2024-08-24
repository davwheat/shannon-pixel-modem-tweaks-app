package dev.davwheat.shannonmodemtweaks.utils

import android.os.Build

object InferDevice {
  private fun getCpuManufacturer(): String {
    return Build.SOC_MANUFACTURER
  }

  private fun getDeviceCodename(): String {
    return Build.BOARD
  }

  /** @return A pair of the device and the certainty that it's a Shannon-based Pixel. */
  fun getDevice(): Pair<PixelDevice, HeuristicsCertainty> {
    val codename = getDeviceCodename()
    val socManufacturer = getCpuManufacturer()

    var checksPassed = 0
    val totalChecks = 2

    val device =
        PixelDevice.getByCodename(codename).let {
          if (it != PixelDevice.UNKNOWN) {
            checksPassed++
          }

          it
        }

    if (socManufacturer != "Google") {
      // uh oh, fucky wucky!
    } else {
      ++checksPassed
    }

    val certainty =
        if (checksPassed >= totalChecks) {
          HeuristicsCertainty.VERY_LIKELY
        } else if (checksPassed >= totalChecks / 2) {
          HeuristicsCertainty.POTENTIALLY
        } else {
          HeuristicsCertainty.VERY_UNLIKELY
        }

    return Pair(device, certainty)
  }

  fun shouldAllowTweaks(): Boolean {
    val (_, certainty) = getDevice()

    return certainty == HeuristicsCertainty.VERY_LIKELY
  }

  enum class PixelDevice(val codename: String?, val humanName: String) {
    UNKNOWN(null, "Unknown"),
    PIXEL_6("oriole", "Google Pixel 6"),
    PIXEL_6_PRO("raven", "Google Pixel 6 Pro"),
    PIXEL_6A("bluejay", "Google Pixel 6a"),
    PIXEL_7("panther", "Google Pixel 7"),
    PIXEL_7_PRO("cheetah", "Google Pixel 7 Pro"),
    PIXEL_7A("lynx", "Google Pixel 7a"),
    PIXEL_FOLD("felix", "Google Pixel Fold"),
    PIXEL_8("shiba", "Google Pixel 8"),
    PIXEL_8_PRO("husky", "Google Pixel 8 Pro"),
    PIXEL_9("tokay", "Google Pixel 9"),
    PIXEL_9_PRO("caiman", "Google Pixel 9 Pro"),
    PIXEL_9_PRO_XL("komodo", "Google Pixel 9 Pro XL"),
    PIXEL_9_PRO_FOLD("comet", "Google Pixel 9 Fold");

    companion object {
      fun getByCodename(codename: String?): PixelDevice {
        for (device in entries) {
          if (device.codename == codename) {
            return device
          }
        }

        return UNKNOWN
      }
    }
  }

  enum class HeuristicsCertainty {
    VERY_LIKELY,
    POTENTIALLY,
    VERY_UNLIKELY,
  }
}
