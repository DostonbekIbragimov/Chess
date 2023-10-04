package uz.chess.masters.utils.compose

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun ClickableText(text: String, fontSize: TextUnit = 12.sp, onclick: () -> Unit) {
    Text(
        text = text,
        fontSize = fontSize,
        color = Color.Green,
        modifier = Modifier.clickable { onclick() }
    )
}