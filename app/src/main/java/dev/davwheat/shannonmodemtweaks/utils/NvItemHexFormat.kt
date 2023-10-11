package dev.davwheat.shannonmodemtweaks.utils

import androidx.annotation.IntRange
import java.util.Locale

fun Int.toNvItemHexString(@IntRange(0, 4) byteCount: Int): String {
  if (byteCount < 0 || byteCount > 4) {
    throw IllegalArgumentException("byteCount must be between 0 and 4 inclusive")
  }

  // Break into bytes from small to large
  val bytes = toByteArray()

  // Limit to max byte count
  val limitedBytes = bytes.sliceArray(0 until byteCount)

  // Convert to hex string, separated with commas
  return limitedBytes
      .joinToString(separator = ",") { "%02X".format(Locale.getDefault(), it) }
      .uppercase(Locale.getDefault())
}

private fun Int.toByteArray(): ByteArray {
  return byteArrayOf(
      (this and 0xFF).toByte(),
      ((this shr 8) and 0xFF).toByte(),
      ((this shr 16) and 0xFF).toByte(),
      ((this shr 24) and 0xFF).toByte(),
  )
}
