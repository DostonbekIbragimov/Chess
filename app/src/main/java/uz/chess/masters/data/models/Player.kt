package uz.chess.masters.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Player(
    var id: String,
    var leftTime: Long,
    var currentPieces: List<Piece>,
    var takenPieces: List<Piece>,
    var isMoveQueue: Boolean
)
