package dev.davwheat.shannonmodemtweaks.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.davwheat.shannonmodemtweaks.utils.InferDevice

@Composable
fun MainScreen() {
  var rootCheckState by rememberSaveable { mutableStateOf(RootCheckState.PENDING) }

  val (device, certainty) = InferDevice.getDevice()

  Column(
      modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState()).fillMaxSize(),
      verticalArrangement = Arrangement.spacedBy(16.dp),
  ) {
    DeviceInfo(device = device, certainty = certainty)
    CheckRootAccess(rootCheckState = rootCheckState) { rootCheckState = it }
  }
}
