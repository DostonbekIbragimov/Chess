package uz.chess.masters.data.repository

import kotlinx.coroutines.flow.Flow
import uz.chess.masters.data.models.Game

interface MainRepository {
    //    fun connectToSocket(): Flow<String>
    suspend fun connect()
    fun receiver(): Flow<String>
    suspend fun moveSend(game: Game)
    suspend fun close()
}