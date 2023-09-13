package uz.chess.masters.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Piece(
    var type: PieceType,
    var position: Position,
    var nextMove: ArrayList<Position> = arrayListOf(),
    var color: PieceColor
)

@Serializable
enum class PieceColor {
    WHITE,
    BLACK
}