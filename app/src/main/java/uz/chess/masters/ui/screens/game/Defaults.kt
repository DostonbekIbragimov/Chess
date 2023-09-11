package uz.chess.masters.ui.screens.game

import uz.chess.masters.model.game.Game
import uz.chess.masters.model.piece.Piece
import uz.chess.masters.model.piece.PieceColor
import uz.chess.masters.model.piece.PieceType
import uz.chess.masters.model.piece.Position
import uz.chess.masters.model.player.Player

/**
 * Created by DostonbekIbragimov on 11/09/2023.
 */
private val blackPieces = arrayListOf(
    Piece(color = PieceColor.BLACK, type = PieceType.ROOK, position = Position(0, 0), nextMove = arrayListOf()),
    Piece(color = PieceColor.BLACK, type = PieceType.KNIGHT, position = Position(1, 0), nextMove = arrayListOf(Position(0, 2), Position(2, 2))),
    Piece(color = PieceColor.BLACK, type = PieceType.BISHOP, position = Position(2, 0), nextMove = arrayListOf()),
    Piece(color = PieceColor.BLACK, type = PieceType.KING, position = Position(3, 0), nextMove = arrayListOf()),
    Piece(color = PieceColor.BLACK, type = PieceType.QUEEN, position = Position(4, 0), nextMove = arrayListOf()),
    Piece(color = PieceColor.BLACK, type = PieceType.BISHOP, position = Position(5, 0), nextMove = arrayListOf()),
    Piece(color = PieceColor.BLACK, type = PieceType.KNIGHT, position = Position(6, 0), nextMove = arrayListOf(Position(5, 2), Position(7, 2))),
    Piece(color = PieceColor.BLACK, type = PieceType.ROOK, position = Position(7, 0), nextMove = arrayListOf()),
    Piece(color = PieceColor.BLACK, type = PieceType.PAWN, position = Position(0, 1), nextMove = arrayListOf(Position(0, 2), Position(0, 3))),
    Piece(color = PieceColor.BLACK, type = PieceType.PAWN, position = Position(1, 1), nextMove = arrayListOf(Position(1, 2), Position(1, 3))),
    Piece(color = PieceColor.BLACK, type = PieceType.PAWN, position = Position(2, 1), nextMove = arrayListOf(Position(2, 2), Position(2, 3))),
    Piece(color = PieceColor.BLACK, type = PieceType.PAWN, position = Position(3, 1), nextMove = arrayListOf(Position(3, 2), Position(3, 3))),
    Piece(color = PieceColor.BLACK, type = PieceType.PAWN, position = Position(4, 1), nextMove = arrayListOf(Position(4, 2), Position(4, 3))),
    Piece(color = PieceColor.BLACK, type = PieceType.PAWN, position = Position(5, 1), nextMove = arrayListOf(Position(5, 2), Position(5, 3))),
    Piece(color = PieceColor.BLACK, type = PieceType.PAWN, position = Position(6, 1), nextMove = arrayListOf(Position(6, 2), Position(6, 3))),
    Piece(color = PieceColor.BLACK, type = PieceType.PAWN, position = Position(7, 1), nextMove = arrayListOf(Position(7, 2), Position(7, 3)))
)

private val whitePieces = arrayListOf(
    Piece(color = PieceColor.WHITE, type = PieceType.ROOK, position = Position(0, 7), nextMove = arrayListOf()),
    Piece(color = PieceColor.WHITE, type = PieceType.KNIGHT, position = Position(1, 7), nextMove = arrayListOf(Position(0, 5), Position(2, 5))),
    Piece(color = PieceColor.WHITE, type = PieceType.BISHOP, position = Position(2, 7), nextMove = arrayListOf()),
    Piece(color = PieceColor.WHITE, type = PieceType.KING, position = Position(3, 7), nextMove = arrayListOf()),
    Piece(color = PieceColor.WHITE, type = PieceType.QUEEN, position = Position(4, 7), nextMove = arrayListOf()),
    Piece(color = PieceColor.WHITE, type = PieceType.BISHOP, position = Position(5, 7), nextMove = arrayListOf()),
    Piece(color = PieceColor.WHITE, type = PieceType.KNIGHT, position = Position(6, 7), nextMove = arrayListOf(Position(5, 5), Position(7, 5))),
    Piece(color = PieceColor.WHITE, type = PieceType.ROOK, position = Position(7, 7), nextMove = arrayListOf()),
    Piece(color = PieceColor.WHITE, type = PieceType.PAWN, position = Position(0, 6), nextMove = arrayListOf(Position(0, 5), Position(0, 4))),
    Piece(color = PieceColor.WHITE, type = PieceType.PAWN, position = Position(1, 6), nextMove = arrayListOf(Position(1, 5), Position(1, 4))),
    Piece(color = PieceColor.WHITE, type = PieceType.PAWN, position = Position(2, 6), nextMove = arrayListOf(Position(2, 5), Position(2, 4))),
    Piece(color = PieceColor.WHITE, type = PieceType.PAWN, position = Position(3, 6), nextMove = arrayListOf(Position(3, 5), Position(3, 4))),
    Piece(color = PieceColor.WHITE, type = PieceType.PAWN, position = Position(4, 6), nextMove = arrayListOf(Position(4, 5), Position(4, 4))),
    Piece(color = PieceColor.WHITE, type = PieceType.PAWN, position = Position(5, 6), nextMove = arrayListOf(Position(5, 5), Position(5, 4))),
    Piece(color = PieceColor.WHITE, type = PieceType.PAWN, position = Position(6, 6), nextMove = arrayListOf(Position(6, 5), Position(6, 4))),
    Piece(color = PieceColor.WHITE, type = PieceType.PAWN, position = Position(7, 6), nextMove = arrayListOf(Position(7, 5), Position(7, 4)))
)

val whitePlayer = Player(leftTime = 10_000, currentPieces = whitePieces, takenPieces = arrayListOf(), isMoveQueue = false)
val blackPlayer = Player(leftTime = 10_000, currentPieces = blackPieces, takenPieces = arrayListOf(), isMoveQueue = false)

fun getBoard(game: Game): ArrayList<ArrayList<Piece?>> {
    val board = ArrayList<ArrayList<Piece?>>()
    for (row in 0 until 8) {
        val rowList = ArrayList<Piece?>()
        for (col in 0 until 8) {

            var piece = game.whitePlayer.currentPieces.find { it.position.x == row && it.position.y == col }
            if (piece == null) {
                piece = game.blackPlayer.currentPieces.find { it.position.x == row && it.position.y == col }
            }
            rowList.add(piece)
        }
        board.add(rowList)
    }
    return board
}

fun getPawnMoves(piece: Piece, board: ArrayList<ArrayList<Piece?>>): List<Position> {
    val moves = mutableListOf<Position>()
    val direction = if (piece.color == PieceColor.BLACK) 1 else -1
    val initialRow = if (piece.color == PieceColor.WHITE) 1 else 6

    // Forward move
    val nextY = piece.position.y + direction
    if (nextY in 0..7 && board[piece.position.x][nextY] == null) {
        moves.add(Position(piece.position.x, nextY))

        // Initial double move
        if (piece.position.x == initialRow) {
            val nextDoubleY = piece.position.y + 2 * direction
            if (board[piece.position.x][nextDoubleY] == null) {
                moves.add(Position(piece.position.x, nextDoubleY))
            }
        }
    }

    // Capturing diagonally
    val captureX1 = piece.position.x - 1
    val captureX2 = piece.position.x + 1
    val captureY = piece.position.y + direction
    if (captureX1 >= 0 && captureY in 0..7 && board[captureX1][captureY]?.color != piece.color) {
        moves.add(Position(captureX1, captureY))
    }
    if (captureX2 <= 7 && captureY in 0..7 && board[captureX2][captureY]?.color != piece.color) {
        moves.add(Position(captureX2, captureY))
    }

    return moves
}

fun getKnightMoves(piece: Piece, board: ArrayList<ArrayList<Piece?>>): List<Position> {
    val moves = mutableListOf<Position>()

    val dx = arrayListOf(1, 1, 2, 2, -1, -1, -2, -2)
    val dy = listOf(2, -2, 1, -1, 2, -2, 1, -1)

    for (i in 0 until 8) {
        val newX = piece.position.x + dx[i]
        val newY = piece.position.y + dy[i]
        if (newX in 0..7 && newY in 0..7 && (board[newX][newY]?.color != piece.color || board[newX][newY] == null)) {
            moves.add(Position(newX, newY))
        }
    }

    return moves
}

fun getBishopMoves(piece: Piece, board: ArrayList<ArrayList<Piece?>>): List<Position> {
    val moves = mutableListOf<Position>()

    val directions = listOf(Pair(1, 1), Pair(1, -1), Pair(-1, 1), Pair(-1, -1))

    for (direction in directions) {
        val dx = direction.first
        val dy = direction.second

        var newX = piece.position.x + dx
        var newY = piece.position.y + dy

        while (newX in 0..7 && newY in 0..7) {
            if (board[newX][newY] == null) {
                moves.add(Position(newX, newY))
            } else if (board[newX][newY]?.color != piece.color) {
                moves.add(Position(newX, newY))
                break
            } else {
                break
            }

            newX += dx
            newY += dy
        }
    }

    return moves
}

fun getRookMoves(piece: Piece, board: ArrayList<ArrayList<Piece?>>): List<Position> {
    val moves = mutableListOf<Position>()

    // Horizontal moves to the right
    for (x in piece.position.x + 1 until 8) {
        if (board[x][piece.position.y] == null) {
            moves.add(Position(x, piece.position.y))
        } else if (board[x][piece.position.y]!!.color != piece.color) {
            moves.add(Position(x, piece.position.y))
            break
        } else {
            break
        }
    }

    // Horizontal moves to the left
    for (x in piece.position.x - 1 downTo 0) {
        if (board[x][piece.position.y] == null) {
            moves.add(Position(x, piece.position.y))
        } else if (board[x][piece.position.y]!!.color != piece.color) {
            moves.add(Position(x, piece.position.y))
            break
        } else {
            break
        }
    }

    // Vertical moves up
    for (y in piece.position.y + 1 until 8) {
        if (board[piece.position.x][y] == null) {
            moves.add(Position(piece.position.x, y))
        } else if (board[piece.position.x][y]!!.color != piece.color) {
            moves.add(Position(piece.position.x, y))
            break
        } else {
            break
        }
    }

    // Vertical moves down
    for (y in piece.position.y - 1 downTo 0) {
        if (board[piece.position.x][y] == null) {
            moves.add(Position(piece.position.x, y))
        } else if (board[piece.position.x][y]!!.color != piece.color) {
            moves.add(Position(piece.position.x, y))
            break
        } else {
            break
        }
    }

    return moves
}

fun getQueenMoves(piece: Piece, board: ArrayList<ArrayList<Piece?>>): List<Position> {
    val rookMoves = getRookMoves(piece, board)
    val bishopMoves = getBishopMoves(piece, board)
    return rookMoves + bishopMoves
}

fun getKingMoves(piece: Piece, board: ArrayList<ArrayList<Piece?>>): List<Position> {
    val moves = mutableListOf<Position>()

    val dx = listOf(-1, -1, -1, 0, 0, 1, 1, 1)
    val dy = listOf(-1, 0, 1, -1, 1, -1, 0, 1)

    for (i in 0 until 8) {
        val newX = piece.position.x + dx[i]
        val newY = piece.position.y + dy[i]
        if (newX in 0..7 && newY in 0..7 && (board[newX][newY]?.color != piece.color || board[newX][newY] == null)) {
            moves.add(Position(newX, newY))
        }
    }

    return moves
}