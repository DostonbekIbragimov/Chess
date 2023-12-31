package uz.chess.masters.model.piece

/**
 * Created by DostonbekIbragimov on 08/09/2023.
 */
data class Piece(
    var type: PieceType,
    var position: Position,
    var nextMove: ArrayList<Position> = arrayListOf(),
    var color: PieceColor
)

enum class PieceColor {
    WHITE,
    BLACK
}