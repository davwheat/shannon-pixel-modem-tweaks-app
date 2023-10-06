package dev.davwheat.shannonmodemtweaks.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.davwheat.shannonmodemtweaks.R
import dev.davwheat.shannonmodemtweaks.utils.InferDevice

@Composable
fun DeviceInfo(
    modifier: Modifier = Modifier,
    device: InferDevice.PixelDevice,
    certainty: InferDevice.HeuristicsCertainty
) {
  Surface(
      modifier = modifier.clip(RoundedCornerShape(8.dp)).fillMaxWidth(),
      shadowElevation = 8.dp,
      tonalElevation = 4.dp,
  ) {
    Column(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
      if (device == InferDevice.PixelDevice.UNKNOWN) {
        Text(
            stringResource(R.string.reports_not_a_pixel),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            stringResource(R.string.non_pixel_damage_warning),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            stringResource(R.string.unknown_device_report),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        return@Column
      }

      when (certainty) {
        InferDevice.HeuristicsCertainty.VERY_LIKELY -> {
          Text(
              stringResource(R.string.reports_pixel_device, device.humanName),
              textAlign = TextAlign.Center,
              modifier = Modifier.fillMaxWidth(),
          )
        }
        InferDevice.HeuristicsCertainty.POTENTIALLY -> {
          Text(
              stringResource(R.string.reports_possible_pixel_device, device.humanName),
              textAlign = TextAlign.Center,
              modifier = Modifier.fillMaxWidth(),
          )
          Text(
              stringResource(R.string.non_pixel_damage_warning),
              textAlign = TextAlign.Center,
              modifier = Modifier.fillMaxWidth(),
          )
        }
        InferDevice.HeuristicsCertainty.VERY_UNLIKELY -> {
          Text(
              stringResource(R.string.reports_unknown_device),
              textAlign = TextAlign.Center,
              modifier = Modifier.fillMaxWidth(),
          )
          Text(
              stringResource(R.string.non_pixel_damage_warning),
              textAlign = TextAlign.Center,
              modifier = Modifier.fillMaxWidth(),
          )
        }
      }
    }
  }
}

@Composable
@Preview
private fun DeviceInfoPreview() {
  DeviceInfo(
      device = InferDevice.PixelDevice.PIXEL_8_PRO,
      certainty = InferDevice.HeuristicsCertainty.VERY_LIKELY,
  )
}
