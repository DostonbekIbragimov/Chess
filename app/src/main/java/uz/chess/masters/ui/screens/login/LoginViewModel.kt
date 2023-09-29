package uz.chess.masters.ui.screens.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import uz.chess.masters.data.api.auth.AuthService
import uz.chess.masters.data.request.LoginRequest
import uz.chess.masters.data.response.LoginResponse
import uz.chess.masters.utils.api.NetworkResult
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val service: AuthService
) : ViewModel() {
    val usernameState = mutableStateOf("")
    val passwordState = mutableStateOf("")

    private val _loginResponseState = Channel<NetworkResult<LoginResponse>>()
    val loginResponseState = _loginResponseState.receiveAsFlow()

    fun login() = viewModelScope.launch(Dispatchers.IO) {
        _loginResponseState.send(NetworkResult.Loading)
        _loginResponseState.send(service.login(LoginRequest(usernameState.value, passwordState.value)))
    }
}