# Contributing guide

Thanks for wanting to contribute to the app!

You'll want Android Studio by JetBrains and Google installed on your device to best develop and test your changes.

## Adding tweaks

Tweaks are designed to be added using a consistent format and technique, while also being very extensible.

All tweaks are found at [`app/sec/main/java/dev/davwheat/shannonmodemtweaks/tweaks`](./app/sec/main/java/dev/davwheat/shannonmodemtweaks/tweaks).

## Adding nvitem tweaks

Nvitem tweaks are the most common tweaks available inside the app, so have the most reusable structure.

The app's code includes an `NvItemTweak` class which handles the logic to actually apply the nvitem changes, spawning a root shell and executing `AT+GOOGSETNV` as needed.

**Let's take a look at an example we already have in the app: enabling NR NSA/SA.**

First, we need to create a new class that extends the `NvItemTweak` class. This class offers abstract properties you should override to define the metadata and changes needed for your intended tweak. Let's call this `NrConfigMode`:

```kt
package dev.davwheat.shannonmodemtweaks.tweaks.nvitems

import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak

class NrConfigMode : NvItemTweak() {
  // We'll add in our overrides here
}
```

Let's start by setting the name and description for our tweak. This will be shown on the Tweaks page in-app, and is important to disclose any important information about a tweak.

```kt
package dev.davwheat.shannonmodemtweaks.tweaks.nvitems

import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak

class NrConfigMode : NvItemTweak() {
  // Tweak name and description to show in the tweaks list.
  // These are overridden from the superclass, NvItemTweak,
  // which also inherits them from the Tweak class.

  override val name = "Enable NSA and SA 5G modes"
  override val description = "Applies to both SIMs"
}
```

Next, you can add the actual nvitem IDs and values. For this, you'll need to override the `nvItems` property.

This property accepts a list of `NvItem` class instances.

```kt
package dev.davwheat.shannonmodemtweaks.tweaks.nvitems

import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak

class NrConfigMode : NvItemTweak() {
  override val name = "Enable NSA and SA 5G modes"
  override val description = "Applies to both SIMs"

  override val nvItems: List<NvItem>
    get() =
        listOf(
          // We'll fill in the nvitems here
        )
}
```

You'll need to fill this list with any and all nvitems and values you'd like to set with your tweak. We can instantiate NvItem objects like below, passing in the nvitem name/id with the `id` constructor parameter, and the value with the `value` parameter.

If your nvitem requires an index, an optional `index` parameter is also present which you can provide.

Additionally, if you need to quicky and easily convert to the little-endian byte-separated hex syntax, such as for setting supported SA bands, the app offers a [helpful extension function on integers called `toNvItemHexString()`](#tonvitemhexstringbytecount-int).

```kt
package dev.davwheat.shannonmodemtweaks.tweaks.nvitems

import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItem
import dev.davwheat.shannonmodemtweaks.tweaks.nvitems.NvItemTweak

class NrConfigMode : NvItemTweak() {
  override val name = "Enable NSA and SA 5G modes"
  override val description = "Applies to both SIMs"

  override val nvItems: List<NvItem>
    get() =
        listOf(
            // Sets allowed NR modes. 00=disabled, 01=NSA, 10=SA, 11=SA+NSA
            NvItem(id = "NR.CONFIG.MODE", value = "11"),
            NvItem(id = "DS.NR.CONFIG.MODE", value = "11"),
        )
}
```

### `toNvItemHexString(byteCount: int)`

If you need to quicky and easily convert to the little-endian byte-separated hex syntax, such as for setting supported SA bands, the app offers a helpful extension function on integers called `toNvItemHexString()`.

You should pass an integer to this method to dictate the number of bytes to output in the resultant string.

This is used in the [`AdditionalNrSaLocking`](./app/sec/main/java/dev/davwheat/shannonmodemtweaks/tweaks/nvitems/AdditionalNrSaLocking.kt) tweak [linked below](#all-nvitem-tweak-features).

```kt
1.toNvItemHexString(3)        // "01,00,00"
78.toNvItemHexString(2)       // "4E,FF"
0xFF0F.toNvItemHexString(2)   // "0F,FF"
0xFFFFFF.toNvItemHexString(2) // "FF,FF"
```

### All nvitem tweak features

The best example of the available features is found in [AdditionalNrSaLocking.kt](./app/sec/main/java/dev/davwheat/shannonmodemtweaks/tweaks/nvitems/AdditionalNrSaLocking.kt).
