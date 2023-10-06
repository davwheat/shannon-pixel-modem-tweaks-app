package dev.davwheat.shannonmodemtweaks.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.davwheat.shannonmodemtweaks.tweaks.AllTweaks
import dev.davwheat.shannonmodemtweaks.tweaks.Tweak
import dev.davwheat.shannonmodemtweaks.ui.theme.ShannonModemTweaksTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TweaksList() {
  val listState = rememberLazyListState()

  LazyColumn(
      state = listState,
      content = {
        AllTweaks.entries.forEach { (category, tweaks) ->
          stickyHeader(key = category) { TweaksCategoryHeader(category = category) }

          items(tweaks) { TweaksListItem(tweak = it) }
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
