package uz.chess.masters.ui.screens.game

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import uz.chess.masters.data.models.Game
import uz.chess.masters.data.models.Player
import uz.chess.masters.data.repository.MainRepository
import uz.chess.masters.utils.TYPE_CONNECT
import uz.chess.masters.utils.TYPE_ERROR
import uz.chess.masters.utils.TYPE_GAME
import uz.chess.masters.utils.TYPE_MOVE
import uz.chess.masters.utils.getDataFromMyServerJson
import uz.chess.masters.utils.getResponseType
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _isConnecting = MutableStateFlow(false)
    val isConnecting = _isConnecting.asStateFlow()

    private val _errorState = Channel<String>()
    val errorState = _errorState.receiveAsFlow()

    var gameState = mutableStateOf(Game())
    var playerState = mutableStateOf<Player?>(null)

    var waitingState = mutableStateOf(false)

    init {
        receiver()
    }

    private fun receiver() {
        viewModelScope.launch {
            repository.receiver().collect { dataStringJson ->
                Log.d("TAG_TEST", "RESPONSE: $dataStringJson")
                try {
                    when (dataStringJson.getResponseType()) {
                        TYPE_CONNECT -> {
                            playerState.value = dataStringJson.getDataFromMyServerJson()
                        }

                        TYPE_GAME -> {
                            gameState.value = dataStringJson.getDataFromMyServerJson()
                            waitingState.value = gameState.value.blackPlayer == null
                        }

                        TYPE_ERROR -> {
                            _errorState.send("Error ")
                        }

                        TYPE_MOVE -> {
                            gameState.value = dataStringJson.getDataFromMyServerJson()
                        }

                    }
                } catch (e: Exception) {
                    Log.d("TAG_TEST", "exception: $e")

                }
            }
        }
    }

    fun connectSocket() {
        viewModelScope.launch {
            repository.connect()
        }
    }

    fun moveSend(game: Game) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.moveSend(game)

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            repository.close()
        }
    }

    fun isMyQueue(): Boolean {
//        return playerState.value?.id == gameState.value.queuePlayerId
        return true
    }

}