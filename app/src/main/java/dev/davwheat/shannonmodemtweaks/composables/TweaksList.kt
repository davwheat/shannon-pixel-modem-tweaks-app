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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import dev.davwheat.shannonmodemtweaks.R
import dev.davwheat.shannonmodemtweaks.tweaks.AllTweaks
import dev.davwheat.shannonmodemtweaks.tweaks.Tweak
import dev.davwheat.shannonmodemtweaks.ui.theme.ShannonModemTweaksTheme
import dev.davwheat.shannonmodemtweaks.utils.InferDevice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TweaksList() {
  val defaultTerminalText = stringResource(R.string.default_terminal_output)

  val listState = rememberLazyListState()
  var outputText by rememberSaveable { mutableStateOf(defaultTerminalText) }
  val allowTweaks = InferDevice.shouldAllowTweaks()
  var isLoadingTweaksState by rememberSaveable { mutableStateOf(allowTweaks) }
  var tweakStateLoadingProgress by rememberSaveable { mutableIntStateOf(0) }
  var tweaksState by rememberSaveable { mutableStateOf<Map<Tweak, Boolean?>>(mapOf()) }

  val allTweaks = AllTweaks.entries
  val tweaksCount = allTweaks.sumOf { it.value.size }

  val outputVerticalScrollState = rememberScrollState()
  val outputHorizontalScrollState = rememberScrollState()

  val scope = rememberCoroutineScope()

  fun appendToOutput(text: String) {
    scope.launch { outputText = outputText + text + "\n" }
  }

  LaunchedEffect(isLoadingTweaksState) {
    if (!isLoadingTweaksState) return@LaunchedEffect

    tweaksState =
        withContext(Dispatchers.IO) {
          val t = allTweaks.flatMap { it.value }

          mapOf<Tweak, Boolean?>(
              *t.map { tweak ->
                    Thread.sleep(50L)
                    tweakStateLoadingProgress += 1
                    Pair(tweak, tweak.isTweakEnabled())
                  }
                  .toTypedArray())
        }

    isLoadingTweaksState = false
  }

  LaunchedEffect(outputText) {
    withContext(Dispatchers.Main) {
      outputVerticalScrollState.animateScrollTo(outputVerticalScrollState.maxValue)
      outputHorizontalScrollState.animateScrollTo(0)
    }
  }

  if (isLoadingTweaksState) {
    Dialog(onDismissRequest = {}) {
      Surface(modifier = Modifier.clip(RoundedCornerShape(8.dp))) {
        Column(modifier = Modifier.padding(24.dp).fillMaxWidth()) {
          Text(
              stringResource(
                  R.string.loading_tweak_states_dialog,
                  tweakStateLoadingProgress,
                  tweaksCount,
              ),
              style = MaterialTheme.typography.bodyLarge,
              modifier = Modifier.padding(bottom = 8.dp),
          )
          Text(
              stringResource(R.string.loading_tweak_states_dialog_description),
              style = MaterialTheme.typography.bodyMedium,
              modifier = Modifier.padding(bottom = 16.dp),
          )
          LinearProgressIndicator(
              progress = tweakStateLoadingProgress.toFloat() / tweaksCount,
              modifier = Modifier.fillMaxWidth(),
          )
        }
      }
    }
  }

  Column(
      modifier = Modifier.fillMaxSize(),
  ) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().weight(66f),
        state = listState,
        content = {
          item {
            IsNsgRunningCheck(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp))
          }

          if (!allowTweaks) {
            item {
              Surface(
                  modifier =
                      Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                          .clip(RoundedCornerShape(8.dp)),
                  tonalElevation = 2.dp,
                  shadowElevation = 2.dp,
              ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(16.dp),
                ) {
                  Text(
                      stringResource(R.string.tweaks_disabled_main),
                      textAlign = TextAlign.Center,
                      fontWeight = FontWeight.Bold,
                  )
                  Text(
                      stringResource(R.string.tweaks_disabled_supporting_1),
                      textAlign = TextAlign.Center,
                      style = MaterialTheme.typography.bodyMedium,
                  )
                  Text(
                      stringResource(R.string.tweaks_disabled_supporting_2),
                      textAlign = TextAlign.Center,
                      style = MaterialTheme.typography.bodyMedium,
                  )
                }
              }
            }
          }

          allTweaks.forEach { (category, tweaks) ->
            stickyHeader(key = category) { TweaksCategoryHeader(category = category) }

            items(tweaks) {
              TweaksListItem(
                  tweak = it,
                  enabled = allowTweaks,
                  onOutput = ::appendToOutput,
                  isTweakApplied = tweaksState[it],
                  onTweakApplied = {
                    scope.launch {
                      val map = tweaksState.toMutableMap()
                      map[it] = withContext(Dispatchers.IO) { it.isTweakEnabled() }
                      tweaksState = map
                    }
                  })
            }
          }
        },
    )

    Box(
        modifier =
            Modifier.weight(34f)
                .background(Color.Black)
                .fillMaxSize()
                .verticalScroll(outputVerticalScrollState)
                .horizontalScroll(outputHorizontalScrollState),
    ) {
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
  Surface(modifier = modifier.fillMaxWidth()) {
    Row(
        modifier =
            Modifier.padding(
                horizontal = 12.dp,
                vertical = 8.dp,
            )) {
          Text(text = category, style = MaterialTheme.typography.titleMedium)
        }
  }
}

@Composable
fun TweaksListItem(
    modifier: Modifier = Modifier,
    tweak: Tweak,
    enabled: Boolean,
    onOutput: (String) -> Unit,
    isTweakApplied: Boolean?,
    onTweakApplied: () -> Unit,
) {
  var isEnabling by rememberSaveable { mutableStateOf(false) }

  fun enableTweak() {
    if (!enabled || (isTweakApplied != false && !tweak.canBeDisabled)) return

    isEnabling = true

    val (success, output) = tweak.applyTweak()

    isEnabling = false

    onOutput(
        "${if (success) "Success" else "Failure"} - ${tweak.name}:\n\n${output}\n${"-".repeat(32)}",
    )
    onTweakApplied()
  }

  Timber.d(tweak.name)
  Timber.d("isEnabling: $isEnabling")
  Timber.d("isTweakApplied: $isTweakApplied")
  Timber.d("-----------")

  Surface(modifier = Modifier.clickable { enableTweak() }) {
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

      Switch(
          checked = isTweakApplied == true || isEnabling,
          enabled = (isTweakApplied == false && !tweak.canBeDisabled) && enabled,
          onCheckedChange = { enableTweak() },
      )
    }
  }
}

@Composable
@Preview
private fun TweaksListPreview() {
  ShannonModemTweaksTheme { TweaksList() }
}
