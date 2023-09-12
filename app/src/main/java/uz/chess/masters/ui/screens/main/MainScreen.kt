package uz.chess.masters.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import uz.chess.masters.ui.screens.Screens

/**
 * Created by DostonbekIbragimov on 08/09/2023.
 */
@Composable
fun MainScreen(
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = {
            navController.navigate(Screens.TEST_GAME.name)
        }) {
            Text("Play")
        }
    }
}