package uz.chess.masters.data.repository

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.WebSocketSession
import io.ktor.http.cio.websocket.close
import io.ktor.http.cio.websocket.readText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import uz.chess.masters.data.models.Game
import uz.chess.masters.utils.TYPE_ERROR
import uz.chess.masters.utils.TYPE_MOVE
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val client: HttpClient
) : MainRepository {

    private var session: WebSocketSession? = null

    private val _receiverState = Channel<String>()

    override fun receiver(): Flow<String> = _receiverState.receiveAsFlow()

    override suspend fun connect() {
        session = client.webSocketSession {
            url("ws://10.10.255.241:8080/play")
        }
        val gameStates = session!!
            .incoming
            .consumeAsFlow()
            .filterIsInstance<Frame.Text>()
            .mapNotNull { it.readText() }

        gameStates.collect {
            _receiverState.send(it)
        }
    }

    override suspend fun moveSend(game: Game) {
        Log.d("TAG_TEST", "send Data: $game")
        try {
            session?.outgoing?.send(Frame.Text("$TYPE_MOVE#${Json.encodeToString(game)}"))
            Log.d("TAG_TEST", "session:$session")

        } catch (e: Exception) {
            _receiverState.send("$TYPE_ERROR#${e.message}")
        }
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