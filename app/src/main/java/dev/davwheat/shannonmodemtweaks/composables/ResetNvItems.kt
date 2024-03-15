package dev.davwheat.shannonmodemtweaks.composables

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
import androidx.compose.material.icons.rounded.Restore
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
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
import dev.davwheat.shannonmodemtweaks.utils.ExecuteAsRoot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

private enum class ResetNvItemsState {
  NONE,
  FAILED_NO_ROOT,
  FAILED_EXECUTION,
  RESETTING,
  CONFIRMATION,
}

private fun resetNvItems(): ResetNvItemsState {
  Timber.d("Wiping nvitems")

  val result =
      ExecuteAsRoot.executeAsRoot(
          listOf(
              "mv /mnt/vendor/efs/nv_normal.bin /mnt/vendor/efs/nv_normal.bin.old;" +
                  "mv /mnt/vendor/efs/nv_normal.bin.md5 /mnt/vendor/efs/nv_normal.bin.md5.old",
          ),
      )

  if (result == null) {
    Timber.d("Result code: failed to execute as root")
    return ResetNvItemsState.FAILED_NO_ROOT
  }

  val code = result[0]?.first

  Timber.d("Wipe nvitems result code: $code")

  return if (code == 0) {
    Timber.d("Successfully wiped nvitems! Rebooting...")
    ExecuteAsRoot.executeAsRoot(
        listOf(
            "reboot",
        ),
    )

    ResetNvItemsState.RESETTING
  } else {
    Timber.d("Failed to wipe nvitems")

    ResetNvItemsState.FAILED_EXECUTION
  }
}

@Composable
fun ResetNvItems(modifier: Modifier = Modifier) {
  var state by rememberSaveable { mutableStateOf(ResetNvItemsState.NONE) }

  val scope = rememberCoroutineScope()

  Surface(
      modifier = modifier.clip(RoundedCornerShape(8.dp)).fillMaxWidth(),
      shadowElevation = 8.dp,
      tonalElevation = 4.dp,
  ) {
    Column(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Text(
          stringResource(R.string.reset_nvitems_lead),
          textAlign = TextAlign.Center,
          fontWeight = FontWeight.Bold,
      )
      Text(
          stringResource(R.string.reset_nvitems_description),
          textAlign = TextAlign.Center,
      )
      OutlinedButton(onClick = { state = ResetNvItemsState.CONFIRMATION }) {
        Icon(
            Icons.Rounded.Restore,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize),
        )
        Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
        Text(stringResource(R.string.reset_nvitems_cta))
      }
    }
  }

  when (state) {
    ResetNvItemsState.NONE -> {}
    ResetNvItemsState.FAILED_NO_ROOT ->
        FailedResetNvItemsNoRootDialog { state = ResetNvItemsState.NONE }
    ResetNvItemsState.FAILED_EXECUTION ->
        FailedResetNvItemsExecutionErrorDialog { state = ResetNvItemsState.NONE }
    ResetNvItemsState.RESETTING -> ResettingNvItemsDialog()
    ResetNvItemsState.CONFIRMATION ->
        ResetNvItemsDialog(
            onDismissRequest = { state = ResetNvItemsState.NONE },
            onConfirm = {
              state = ResetNvItemsState.RESETTING
              scope.launch {
                withContext(Dispatchers.IO) {
                  val result = resetNvItems()

                  if (result != ResetNvItemsState.RESETTING) {
                    // Failed to wipe nvitems
                    state = result
                  }
                }
              }
            },
        )
  }
}

@Composable
private fun ResetNvItemsDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
) {
  AlertDialog(
      onDismissRequest = onDismissRequest,
      confirmButton = {
        TextButton(onClick = onConfirm) {
          Text(stringResource(R.string.reset_nvitems_dialog_confirm_cta))
        }
      },
      dismissButton = {
        TextButton(onClick = onDismissRequest) {
          Text(stringResource(R.string.reset_nvitems_dialog_dismiss_cta))
        }
      },
      title = { Text(stringResource(R.string.reset_nvitems_dialog_title)) },
      text = {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Text(stringResource(R.string.reset_nvitems_dialog_lead))
          Text(stringResource(R.string.reset_nvitems_dialog_description))
        }
      },
  )
}

@Composable
private fun ResettingNvItemsDialog() {
  AlertDialog(
      onDismissRequest = {},
      confirmButton = {},
      dismissButton = {},
      title = { Text(stringResource(R.string.reset_nvitems_resetting_dialog_title)) },
      text = {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
          CircularProgressIndicator()

          Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(stringResource(R.string.reset_nvitems_resetting_dialog_lead))
            Text(stringResource(R.string.reset_nvitems_resetting_dialog_description))
          }
        }
      },
  )
}

@Composable
private fun FailedResetNvItemsNoRootDialog(
    onDismissRequest: () -> Unit,
) {
  AlertDialog(
      onDismissRequest = onDismissRequest,
      confirmButton = {
        TextButton(onClick = onDismissRequest) {
          Text(stringResource(R.string.reset_nvitems_failed_dialog_confirm_cta))
        }
      },
      title = { Text(stringResource(R.string.reset_nvitems_failed_dialog_title)) },
      text = {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Text(stringResource(R.string.reset_nvitems_failed_dialog_lead))
          Text(stringResource(R.string.reset_nvitems_failed_dialog_no_root))
        }
      },
  )
}

@Composable
private fun FailedResetNvItemsExecutionErrorDialog(
    onDismissRequest: () -> Unit,
) {
  AlertDialog(
      onDismissRequest = onDismissRequest,
      confirmButton = {
        TextButton(onClick = onDismissRequest) {
          Text(stringResource(R.string.reset_nvitems_failed_dialog_confirm_cta))
        }
      },
      title = { Text(stringResource(R.string.reset_nvitems_failed_dialog_title)) },
      text = {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Text(stringResource(R.string.reset_nvitems_failed_dialog_lead))
          Text(stringResource(R.string.reset_nvitems_failed_dialog_execution_error))
        }
      },
  )
}

@Composable
@Preview
private fun ResetNvItemsPreview() {
  ResetNvItems()
}

@Composable
@Preview
private fun ResetNvItemsDialogPreview() {
  ResetNvItemsDialog(
      onDismissRequest = {},
      onConfirm = {},
  )
}

@Composable
@Preview
private fun ResettingNvItemsDialogPreview() {
  ResettingNvItemsDialog()
}

@Composable
@Preview
private fun FailedResetNvItemsNoRootDialogPreview() {
  FailedResetNvItemsNoRootDialog {}
}

@Composable
@Preview
private fun FailedResetNvItemsExecutionErrorDialogPreview() {
  FailedResetNvItemsExecutionErrorDialog {}
}
