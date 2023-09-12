package uz.chess.masters.data.repository

import android.nfc.Tag
import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.WebSocketSession
import io.ktor.http.cio.websocket.close
import io.ktor.http.cio.websocket.readText
import io.ktor.utils.io.printStack
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import uz.chess.masters.data.models.Game
import uz.chess.masters.utils.TYPE_ERROR
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val client: HttpClient
) : MainRepository {

    private var session: WebSocketSession? = null

    private val _receiverState = Channel<String>()

    override fun receiver(): Flow<String> = _receiverState.receiveAsFlow()

    override suspend fun connect() {
        try {
            client.webSocket(method = HttpMethod.Get, host = "10.10.255.241", port = 8080, path = "/play") {
                while (true) {
                    val dataStringJson = incoming.receive() as? Frame.Text
                    _receiverState.send(dataStringJson?.readText() ?: "$TYPE_ERROR#Empty data")
                }
            }
        } catch (e: Exception) {
            _receiverState.send("$TYPE_ERROR#${e.message}")
        }
    }


    override suspend fun sendAction(game: Game) {
        session?.outgoing?.send(
            Frame.Text(Json.encodeToString(game))
        )
    }

    override suspend fun close() {
        session?.close()
        session = null
    }

//    private fun socketReceiver() {
//        gameScope.launch {
//            session!!.incoming
//                .consumeAsFlow()
//                .filterIsInstance<Frame.Text>()
//                .mapNotNull {
//                    val type = it.readText().substringBefore('#')
//                    val dataString = it.readText().substringAfter('#')
//                    when (type) {
//                        TYPE_CONNECT -> {
//                            connectState.send(Json.decodeFromString(dataString))
//                        }
//
//                        TYPE_GAME -> {
//                            gameState.send(Json.decodeFromString(dataString))
//                        }
//                    }
//                    Json.decodeFromString<Game>(it.readText())
//                }
//        }
//    }

}