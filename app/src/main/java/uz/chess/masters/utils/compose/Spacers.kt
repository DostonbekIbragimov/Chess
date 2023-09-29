package uz.chess.masters.utils.compose

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ColumnScope.SpacerFillAvailableWeight(weight: Float = 1f) {
    Spacer(modifier = Modifier.weight(weight))
}


@Composable
fun Spacer10dp() {
    Spacer(modifier = Modifier.size(10.dp))
}

@Composable
fun Spacer12dp() {
    Spacer(modifier = Modifier.size(12.dp))
}
@Composable
fun Spacer16dp() {
    Spacer(modifier = Modifier.size(16.dp))
}

@Composable
fun Spacer24dp() {
    Spacer(modifier = Modifier.size(24.dp))
}

@Composable
fun Spacer32dp() {
    Spacer(modifier = Modifier.size(32.dp))
}

@Composable
fun Spacer48dp() {
    Spacer(modifier = Modifier.size(48.dp))
}

@Composable
fun Spacer56dp() {
    Spacer(modifier = Modifier.size(56.dp))
}
