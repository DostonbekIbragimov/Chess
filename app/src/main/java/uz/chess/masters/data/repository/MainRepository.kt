package uz.chess.masters.data.repository

import kotlinx.coroutines.flow.Flow
import uz.chess.masters.data.models.Game
import uz.chess.masters.data.models.Player
import java.nio.channels.Channel

interface MainRepository {
    //    fun connectToSocket(): Flow<String>
    suspend fun connect()
    fun receiver(): Flow<String>
    suspend fun sendAction(game: Game)
    suspend fun close()
}