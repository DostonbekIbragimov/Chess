package uz.chess.masters.data.models

import kotlinx.serialization.Serializable
import uz.chess.masters.R

//@Serializable
//enum class PieceType(val value: Int) {
//    PAWN(1),
//    ROOK(4),
//    KNIGHT(3),
//    BISHOP(3),
//    QUEEN(9),
//    KING(0)
//}

@Serializable
enum class PieceType {
    PAWN,
    ROOK,
    KNIGHT,
    BISHOP,
    QUEEN,
    KING
}


fun PieceType.getPieceIcon(white: Boolean): Int {
    return when (this) {
        PieceType.PAWN -> if (white) R.drawable.pawn_white else R.drawable.pawn_black
        PieceType.ROOK -> if (white) R.drawable.rook_white else R.drawable.rook_black
        PieceType.KNIGHT -> if (white) R.drawable.knight_white else R.drawable.knight_black
        PieceType.BISHOP -> if (white) R.drawable.bishop_white else R.drawable.ic_bishop_black
        PieceType.QUEEN -> if (white) R.drawable.queen_white else R.drawable.queen_black
        PieceType.KING -> if (white) R.drawable.king_white else R.drawable.king_black
    }
}