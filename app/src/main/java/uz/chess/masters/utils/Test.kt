package uz.chess.masters.utils

import uz.chess.masters.model.piece.Piece
import uz.chess.masters.model.piece.PieceType
import uz.chess.masters.model.piece.Position

fun getKnightMoves(piece: Piece): List<Pair<Int, Int>> {
    val moves = mutableListOf<Pair<Int, Int>>()

    val dx = listOf(1, 1, 2, 2, -1, -1, -2, -2)
    val dy = listOf(2, -2, 1, -1, 2, -2, 1, -1)

    for (i in 0 until 8) {
        val newX = piece.position.x + dx[i]
        val newY = piece.position.y + dy[i]
        if (newX in 0..7 && newY in 0..7) {
            moves.add(Pair(newX, newY))
        }
    }

    return moves
}


fun main(){
}