package uz.chess.masters.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val whitePlayer: Player? = null,
    val blackPlayer: Player? = null,
    val gameState: GameStateData = GameStateData()
)