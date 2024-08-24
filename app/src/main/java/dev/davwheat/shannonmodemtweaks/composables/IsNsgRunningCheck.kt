package dev.davwheat.shannonmodemtweaks.composables

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.davwheat.shannonmodemtweaks.R
import dev.davwheat.shannonmodemtweaks.utils.IsNsgRunning
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun IsNsgRunningCheck(
  modifier: Modifier = Modifier,
) {

  var isNsgRunning by rememberSaveable { mutableStateOf<Boolean?>(null) }
  var isNsgRunningRefreshing by rememberSaveable { mutableStateOf(false) }

  fun refreshNsgStatus() {
    isNsgRunningRefreshing = true
    CoroutineScope(Dispatchers.IO).launch {
      val running = IsNsgRunning.isNsgRunning()

      withContext(Dispatchers.Main) {
        isNsgRunning = running
        isNsgRunningRefreshing = false
      }
    }
  }

  LaunchedEffect(true) { if (isNsgRunning == null) refreshNsgStatus() }

  AnimatedContent(targetState = isNsgRunning, label = "NSG running check content") {
    if (it == true) {
      IsNsgRunningCheckResult(modifier, isNsgRunningRefreshing) { refreshNsgStatus() }
    }
  }
}

@Composable
private fun IsNsgRunningCheckResult(
  modifier: Modifier = Modifier,
  isRefreshing: Boolean,
  onRecheck: () -> Unit,
) {
  Surface(
    modifier = modifier
      .clip(RoundedCornerShape(8.dp))
      .fillMaxWidth(),
    color = MaterialTheme.colorScheme.errorContainer,
    contentColor = MaterialTheme.colorScheme.onErrorContainer,
    tonalElevation = 2.dp,
    shadowElevation = 2.dp,
  ) {
    Column(
      verticalArrangement = Arrangement.spacedBy(16.dp),
      modifier = Modifier.padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Text(
        stringResource(R.string.nsg_running_warning),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
      )

      Button(
        enabled = !isRefreshing,
        onClick = { onRecheck() },
        colors =
        ButtonDefaults.buttonColors(
          containerColor = MaterialTheme.colorScheme.error,
          contentColor = MaterialTheme.colorScheme.onError,
        ),
      ) {
        if (isRefreshing) {
          CircularProgressIndicator(
            modifier = Modifier.size(ButtonDefaults.IconSize),
            strokeWidth = 2.dp,
            color = LocalContentColor.current,
          )
        } else {
          Icon(
            Icons.Rounded.Refresh,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize),
          )
        }
        Spacer(Modifier.width(ButtonDefaults.IconSpacing))
        Text(stringResource(R.string.nsg_check_refresh))
      }
    }
  }
}

@Preview
@Composable
private fun IsNsgRunningPreview() {
  Column(
    verticalArrangement = Arrangement.spacedBy(8.dp),
  ) {
    IsNsgRunningCheckResult(isRefreshing = true) {}
    IsNsgRunningCheckResult(isRefreshing = false) {}
  }
}
