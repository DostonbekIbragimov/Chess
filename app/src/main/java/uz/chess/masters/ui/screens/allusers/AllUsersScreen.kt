package uz.chess.masters.ui.screens.allusers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import uz.chess.masters.data.response.UserData
import uz.chess.masters.ui.screens.Screens
import uz.chess.masters.ui.screens.login.LoginUserViewModel
import uz.chess.masters.utils.api.NetworkResult
import uz.chess.masters.utils.compose.ClickableText
import uz.chess.masters.utils.compose.PrimaryButton
import uz.chess.masters.utils.compose.Spacer12dp
import uz.chess.masters.utils.compose.Spacer24dp
import uz.chess.masters.utils.compose.Spacer48dp
import uz.chess.masters.utils.showToast

@Composable
fun AllUsersScreen(
    navController: NavController,
    viewModel: AllUsersViewModel = hiltViewModel()
) {

    var usersState by remember {
        mutableStateOf<List<UserData>>(listOf())
    }

    val context = LocalContext.current
    var loadingState by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getAllUsers()

        viewModel.allUsersResponseState.collect {
            loadingState = when (it) {
                is NetworkResult.Error -> {
                    showToast(context, it.message)
                    false
                }

                NetworkResult.Loading -> {
                    true
                }

                is NetworkResult.Success -> {
                    usersState = it.data
                    false
                }
            }
        }
    })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp), contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All users")
            Spacer12dp()
            usersState.forEach {
                UserItemView(userData = it)
            }
        }
    }
}