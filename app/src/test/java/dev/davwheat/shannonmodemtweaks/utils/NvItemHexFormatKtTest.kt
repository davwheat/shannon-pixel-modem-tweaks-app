package dev.davwheat.shannonmodemtweaks.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class NvItemHexFormatKtTest {

  @Test
  fun toNvItemHexString() {
    assertEquals("01,00,00,00", 1.toNvItemHexString(4))
    assertEquals("01,00", 1.toNvItemHexString(2))
    assertEquals("01", 1.toNvItemHexString(1))
    assertEquals("", 1.toNvItemHexString(0))

    assertEquals("FF,00", 0xFF.toNvItemHexString(2))
    assertEquals("FF,FF", 0xFFFF.toNvItemHexString(2))
    assertEquals("FF,FF", 0xFFFFFF.toNvItemHexString(2))
    assertEquals("FF,FF", Int.MAX_VALUE.toNvItemHexString(2))

    assertEquals("00,FF", 0xFF00.toNvItemHexString(2))
    assertEquals("00,00", 0xFF0000.toNvItemHexString(2))

    assertThrows<IllegalArgumentException> { 0x0.toNvItemHexString(-1) }

    assertThrows<IllegalArgumentException> { 0x0.toNvItemHexString(5) }
  }
}
