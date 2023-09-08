package uz.chess.masters.utils.extensions

import androidx.compose.ui.graphics.Color

/**
 * Created by DostonbekIbragimov on 08/09/2023.
 */
fun Int.getCellColor(): Color {
    return when ((this + 1) % 16) {
        1, 3, 5, 7, 10, 12, 14, 0 -> Color(0xFFFFCE9E)
        else -> Color(0xFFD18B47)
    }
}