package uz.chess.masters.ui.screens.allusers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import uz.chess.masters.data.response.UserData

@Composable
fun UserItemView(userData: UserData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp, start = 12.dp, end = 12.dp)
            .clip(
                RoundedCornerShape(12.dp)
            )
            .background(Color.White)
            .padding(12.dp)

    ) {
        Column(Modifier.weight(1f)) {
            Text(text = "Full name: ", color = Color.Green)
            Text(text = "Username: ", color = Color.Green)
        }
        Column(Modifier.weight(1f)) {
            Text(text = userData.fullName, color = Color.Black)
            Text(text = userData.username, color = Color.Black)
        }
    }
}