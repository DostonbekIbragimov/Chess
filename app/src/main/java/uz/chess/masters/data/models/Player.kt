package uz.chess.masters.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Player(
    var id: String,
    var leftTime: Long,
    var currentPieces: ArrayList<Piece>,
    var takenPieces: ArrayList<Piece>,
)
