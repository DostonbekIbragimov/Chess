package uz.chess.masters.ui.screens.allusers

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
import uz.chess.masters.data.api.main.MainService
import uz.chess.masters.data.request.CreateUserRequest
import uz.chess.masters.data.response.UserData
import uz.chess.masters.utils.api.NetworkResult
import javax.inject.Inject

@HiltViewModel
class AllUsersViewModel @Inject constructor(
    private val service: MainService
) : ViewModel() {

    private val _allUsersResponseState = Channel<NetworkResult<List<UserData>>>()
    val allUsersResponseState = _allUsersResponseState.receiveAsFlow()

    fun getAllUsers() = viewModelScope.launch(Dispatchers.IO) {
        _allUsersResponseState.send(NetworkResult.Loading)
        _allUsersResponseState.send(service.getAllUsers())
    }
}