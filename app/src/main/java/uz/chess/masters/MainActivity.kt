package uz.chess.masters

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uz.chess.masters.ui.screens.Screens
import uz.chess.masters.ui.screens.game.GameScreen
import uz.chess.masters.ui.screens.main.MainScreen
import uz.chess.masters.ui.theme.ChessMastersTheme
import java.text.SimpleDateFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ChessMastersTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppNavHost(navController)
                }
            }
        }
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screens.GAME.name) {
        composable(Screens.MAIN.name) {
            MainScreen(navController = navController)
        }

        composable(Screens.GAME.name) {
            GameScreen(navController = navController)
        }
    }
}