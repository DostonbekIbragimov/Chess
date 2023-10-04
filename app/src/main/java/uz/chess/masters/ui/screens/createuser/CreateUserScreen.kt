package uz.chess.masters.ui.screens.createuser

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import uz.chess.masters.utils.api.NetworkResult
import uz.chess.masters.utils.compose.PrimaryButton
import uz.chess.masters.utils.compose.Spacer10dp
import uz.chess.masters.utils.compose.Spacer12dp
import uz.chess.masters.utils.compose.Spacer48dp
import uz.chess.masters.utils.showToast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateUserScreen(
    navController: NavController,
    viewModel: CreateUserViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    var loadingState by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit, block = {
        viewModel.createUserResponseState.collect {
            loadingState = when (it) {
                is NetworkResult.Error -> {
                    showToast(context, it.message)
                    false
                }

                NetworkResult.Loading -> {
                    true
                }

                is NetworkResult.Success -> {
                    showToast(context, it.data.jwt.toString())
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
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Sign up")
            Spacer12dp()
            TextField(value = viewModel.usernameState.value, onValueChange = { viewModel.usernameState.value = it }, placeholder = { Text(text = "username") })
            Spacer10dp()
            TextField(value = viewModel.fullNameState.value, onValueChange = { viewModel.fullNameState.value = it }, placeholder = { Text(text = "full name") })
            Spacer10dp()
            TextField(value = viewModel.passwordState.value, onValueChange = { viewModel.passwordState.value = it }, placeholder = { Text(text = "password") })
            Spacer48dp()
            PrimaryButton(text = "sign up") {
                viewModel.create()
            }
        }
    }
}