package dev.davwheat.shannonmodemtweaks.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
  var outputText by rememberSaveable { mutableStateOf("Run a tweak to see its output here.\n\n") }
  val allowTweaks = InferDevice.shouldAllowTweaks()

  Column(
      modifier = Modifier.fillMaxSize(),
  ) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(top = 16.dp).weight(66f),
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
    )

    Box(
        modifier =
            Modifier.weight(34f)
                .background(Color.Black)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .horizontalScroll(rememberScrollState())) {
          SelectionContainer {
            Text(
                text = outputText,
                modifier = Modifier.padding(8.dp),
                color = Color.White,
                style =
                    TextStyle(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Normal,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    ),
            )
          }
        }
  }
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
fun TweaksListItem(
    modifier: Modifier = Modifier,
    tweak: Tweak,
    enabled: Boolean,
    onOutput: (String) -> Unit
) {
  Surface(
      modifier =
          Modifier.clickable {
            if (enabled) {
              val (success, output) = tweak.applyTweak()

              onOutput(
                  "${if (success) "Success" else "Failure"} - ${tweak.name}:\n\n${output}\n${"-".repeat(32)}",
              )
            }
          }) {
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
