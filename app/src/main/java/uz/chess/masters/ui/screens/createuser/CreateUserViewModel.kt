package uz.chess.masters.ui.screens.createuser

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import uz.chess.masters.data.response.CreateUserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import uz.chess.masters.data.api.auth.AuthService
import uz.chess.masters.data.request.CreateUserRequest
import uz.chess.masters.utils.api.NetworkResult
import javax.inject.Inject

@HiltViewModel
class CreateUserViewModel @Inject constructor(
    private val service: AuthService
) : ViewModel() {
    val usernameState = mutableStateOf("")
    val fullNameState = mutableStateOf("")
    val passwordState = mutableStateOf("")

    private val _createUserResponseState = Channel<NetworkResult<CreateUserResponse>>()
    val createUserResponseState = _createUserResponseState.receiveAsFlow()

    fun create() = viewModelScope.launch(Dispatchers.IO) {
        _createUserResponseState.send(NetworkResult.Loading)
        _createUserResponseState.send(service.createUser(CreateUserRequest(usernameState.value, passwordState.value, fullNameState.value)))
    }
}