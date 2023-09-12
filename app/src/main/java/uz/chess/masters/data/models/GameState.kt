package uz.chess.masters.data.models

import kotlinx.serialization.Serializable

@Serializable
enum class GameState {
    PENDING,
    STARTED,
    GAME_OVER
}

@Serializable
data class GameStateData(
    val state: GameState = GameState.PENDING,
    val winner: Player? = null
)

