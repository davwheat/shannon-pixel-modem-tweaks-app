package dev.davwheat.shannonmodemtweaks.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.davwheat.shannonmodemtweaks.tweaks.AllTweaks
import dev.davwheat.shannonmodemtweaks.tweaks.Tweak
import dev.davwheat.shannonmodemtweaks.ui.theme.ShannonModemTweaksTheme
import dev.davwheat.shannonmodemtweaks.utils.InferDevice

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TweaksList() {
  val listState = rememberLazyListState()
  val allowTweaks = InferDevice.shouldAllowTweaks()

    LazyColumn(
        state = listState,
        content = {
          if (!allowTweaks) {
            item {
              Surface(
                  modifier = Modifier.padding(16.dp).clip(RoundedCornerShape(8.dp)),
                  tonalElevation = 2.dp,
                  shadowElevation = 2.dp,
              ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(16.dp),
                ) {
                  Text(
                      "We can't be certain that your device is a Google Pixel with a Shannon modem.",
                      textAlign = TextAlign.Center,
                      fontWeight = FontWeight.Bold,
                  )
                  Text(
                      "These tweaks and settings will only work on a Google Pixel with a Shannon modem.",
                      textAlign = TextAlign.Center,
                      style = MaterialTheme.typography.bodyMedium,
                  )
                  Text(
                      "Until we can determine your device is compatible, you cannot apply any tweaks.",
                      textAlign = TextAlign.Center,
                      style = MaterialTheme.typography.bodyMedium,
                  )
                }
              }
            }
          }

          AllTweaks.entries.forEach { (category, tweaks) ->
            stickyHeader(key = category) { TweaksCategoryHeader(category = category) }

            items(tweaks) {
              TweaksListItem(
                  tweak = it,
                  enabled = allowTweaks,
                  onOutput = { outputText = outputText + it + "\n" },
              )
            }
          }
        },
      modifier = Modifier.padding(top = 16.dp),
  )
}

@Composable
fun TweaksCategoryHeader(modifier: Modifier = Modifier, category: String) {
  Row(
      modifier =
          modifier.padding(
              horizontal = 12.dp,
              vertical = 8.dp,
          )) {
        Text(text = category, style = MaterialTheme.typography.titleMedium)
      }
}

@Composable
fun TweaksListItem(modifier: Modifier = Modifier, tweak: Tweak) {
  Surface(modifier = Modifier.clickable { tweak.applyTweak() }) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(vertical = 12.dp, horizontal = 16.dp),
    ) {
      Column(
          modifier = Modifier.weight(1f).padding(end = 16.dp),
      ) {
        Text(
            text = tweak.name,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 8.dp),
        )
        Text(text = tweak.description, style = MaterialTheme.typography.bodyMedium)
      }
    }
  }
}

@Composable
@Preview
private fun TweaksListPreview() {
  ShannonModemTweaksTheme { TweaksList() }
}
