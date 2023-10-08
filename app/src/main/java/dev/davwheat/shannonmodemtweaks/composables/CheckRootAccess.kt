package dev.davwheat.shannonmodemtweaks.composables

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.GppBad
import androidx.compose.material.icons.rounded.GppGood
import androidx.compose.material.icons.rounded.Security
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.davwheat.shannonmodemtweaks.R
import dev.davwheat.shannonmodemtweaks.utils.ExecuteAsRoot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

enum class RootCheckState {
  PENDING,
  FAILED,
  PASSED,
}

@Composable
fun CheckRootAccess(
    modifier: Modifier = Modifier,
    rootCheckState: RootCheckState,
    onUpdateRootCheckState: (RootCheckState) -> Unit = {}
) {
  var loadingRootCheck by rememberSaveable { mutableStateOf(false) }

  Surface(
      modifier = modifier.clip(RoundedCornerShape(8.dp)).fillMaxWidth(),
      shadowElevation = 8.dp,
      tonalElevation = 4.dp,
  ) {
    AnimatedContent(targetState = rootCheckState, label = "Root check") {
      if (it == RootCheckState.PASSED) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Rounded.GppGood,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(end = 8.dp),
            )
            Text(
                stringResource(R.string.root_access_success),
                textAlign = TextAlign.Center,
            )
          }
        }
      } else {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
          if (it == RootCheckState.FAILED) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                  Icons.Rounded.GppBad,
                  contentDescription = null,
                  tint = MaterialTheme.colorScheme.error,
                  modifier = Modifier.padding(end = 8.dp),
              )
              Text(
                  stringResource(R.string.root_access_fail),
                  textAlign = TextAlign.Center,
              )
            }
          }

          Text(
              stringResource(R.string.root_access_request),
              textAlign = TextAlign.Center,
          )

          Button(
              modifier = Modifier.padding(top = 4.dp),
              enabled = !loadingRootCheck,
              onClick = {
                loadingRootCheck = true
                CoroutineScope(Dispatchers.IO).launch {
                  val hasRoot = ExecuteAsRoot.hasRoot()

                  MainScope().launch {
                    onUpdateRootCheckState(
                        if (hasRoot) RootCheckState.PASSED else RootCheckState.FAILED,
                    )
                    loadingRootCheck = false
                  }
                }
              },
          ) {
            Row {
              if (loadingRootCheck) {
                CircularProgressIndicator(
                  modifier = Modifier.size(ButtonDefaults.IconSize),
                  strokeWidth = 2.dp,
                )
              } else {
                Icon(
                    Icons.Rounded.Security,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                )
              }
              Spacer(Modifier.width(ButtonDefaults.IconSpacing))
              Text(stringResource(R.string.root_access_request_cta))
            }
          }
        }
      }
    }
  }
}
