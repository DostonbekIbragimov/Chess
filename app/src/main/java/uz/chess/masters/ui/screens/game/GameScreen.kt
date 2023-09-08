package uz.chess.masters.ui.screens.game

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import uz.chess.masters.model.piece.Piece
import uz.chess.masters.model.piece.PieceType
import uz.chess.masters.model.piece.Position
import uz.chess.masters.model.piece.getPieceIcon
import uz.chess.masters.utils.extensions.getCellColor

/**
 * Created by DostonbekIbragimov on 08/09/2023.
 */
@SuppressLint("UnrememberedMutableState")
@Composable
fun GameScreen(
    navController: NavController
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp

    val moveAbleList = mutableStateListOf<Position>()
    val squareWidth = screenWidth / 8

    val blackPieces = mutableStateListOf(
        Piece(type = PieceType.ROOK, position = Position(0, 0), nextMove = listOf()),
        Piece(type = PieceType.KNIGHT, position = Position(1, 0), nextMove = listOf(Position(0, 2), Position(2, 2))),
        Piece(type = PieceType.BISHOP, position = Position(2, 0), nextMove = listOf()),
        Piece(type = PieceType.KING, position = Position(3, 0), nextMove = listOf()),
        Piece(type = PieceType.QUEEN, position = Position(4, 0), nextMove = listOf()),
        Piece(type = PieceType.BISHOP, position = Position(5, 0), nextMove = listOf()),
        Piece(type = PieceType.KNIGHT, position = Position(6, 0), nextMove = listOf(Position(5, 2), Position(7, 2))),
        Piece(type = PieceType.ROOK, position = Position(7, 0), nextMove = listOf()),
        Piece(type = PieceType.PAWN, position = Position(0, 1), nextMove = listOf(Position(0, 2), Position(0, 3))),
        Piece(type = PieceType.PAWN, position = Position(1, 1), nextMove = listOf(Position(1, 2), Position(1, 3))),
        Piece(type = PieceType.PAWN, position = Position(2, 1), nextMove = listOf(Position(2, 2), Position(2, 3))),
        Piece(type = PieceType.PAWN, position = Position(3, 1), nextMove = listOf(Position(3, 2), Position(3, 3))),
        Piece(type = PieceType.PAWN, position = Position(4, 1), nextMove = listOf(Position(4, 2), Position(4, 3))),
        Piece(type = PieceType.PAWN, position = Position(5, 1), nextMove = listOf(Position(5, 2), Position(5, 3))),
        Piece(type = PieceType.PAWN, position = Position(6, 1), nextMove = listOf(Position(6, 2), Position(6, 3))),
        Piece(type = PieceType.PAWN, position = Position(7, 1), nextMove = listOf(Position(7, 2), Position(7, 3)))
    )

    val whitePieces = mutableStateListOf(
        Piece(type = PieceType.ROOK, position = Position(0, 7), nextMove = listOf()),
        Piece(type = PieceType.KNIGHT, position = Position(1, 7), nextMove = listOf(Position(0, 5), Position(2, 5))),
        Piece(type = PieceType.BISHOP, position = Position(2, 7), nextMove = listOf()),
        Piece(type = PieceType.KING, position = Position(3, 7), nextMove = listOf()),
        Piece(type = PieceType.QUEEN, position = Position(4, 7), nextMove = listOf()),
        Piece(type = PieceType.BISHOP, position = Position(5, 7), nextMove = listOf()),
        Piece(type = PieceType.KNIGHT, position = Position(6, 7), nextMove = listOf(Position(5, 5), Position(7, 5))),
        Piece(type = PieceType.ROOK, position = Position(7, 7), nextMove = listOf()),
        Piece(type = PieceType.PAWN, position = Position(0, 6), nextMove = listOf(Position(0, 5), Position(0, 4))),
        Piece(type = PieceType.PAWN, position = Position(1, 6), nextMove = listOf(Position(1, 5), Position(1, 4))),
        Piece(type = PieceType.PAWN, position = Position(2, 6), nextMove = listOf(Position(2, 5), Position(2, 4))),
        Piece(type = PieceType.PAWN, position = Position(3, 6), nextMove = listOf(Position(3, 5), Position(3, 4))),
        Piece(type = PieceType.PAWN, position = Position(4, 6), nextMove = listOf(Position(4, 5), Position(4, 4))),
        Piece(type = PieceType.PAWN, position = Position(5, 6), nextMove = listOf(Position(5, 5), Position(5, 4))),
        Piece(type = PieceType.PAWN, position = Position(6, 6), nextMove = listOf(Position(6, 5), Position(6, 4))),
        Piece(type = PieceType.PAWN, position = Position(7, 6), nextMove = listOf(Position(7, 5), Position(7, 4)))
    )

    var selectedPiece: Piece? = null

    var isWhitePlayer = true

    fun updateNextMoves() {

    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LazyVerticalGrid(columns = GridCells.Fixed(8),
            content = {
                items(64) { index ->
                    val x = index % 8
                    val y = index / 8
                    val blackPiece = blackPieces.find { it.position.x == x && it.position.y == y }
                    val whitePiece = whitePieces.find { it.position.x == x && it.position.y == y }
                    val moveAbleItem = moveAbleList.find { it.x == x && it.y == y }

                    Box(
                        modifier = Modifier
                            .size(squareWidth.dp)
                            .background(color = index.getCellColor())
                            .clickable(interactionSource = remember { MutableInteractionSource() }, indication = rememberRipple()) {
                                moveAbleList.clear()
                                if (selectedPiece != null) {
                                    val newSelectedCell = selectedPiece!!.nextMove.find { it.x == x && it.y == y }
                                    if (newSelectedCell != null) {
                                        if (isWhitePlayer) {
                                            whitePieces.find { it.position == selectedPiece!!.position }?.position = newSelectedCell
                                            whitePieces.find { it.position == selectedPiece!!.position }?.nextMove = listOf()
                                        } else {
                                            blackPieces.find { it.position == selectedPiece!!.position }?.position = newSelectedCell
                                            blackPieces.find { it.position == selectedPiece!!.position }?.nextMove = listOf()
                                        }
                                        updateNextMoves()
                                        isWhitePlayer = !isWhitePlayer
                                    }
                                }
                                if (isWhitePlayer) {
                                    if (whitePiece != null && !whitePiece.nextMove.isNullOrEmpty()) {
                                        moveAbleList.addAll(whitePiece.nextMove)
                                        selectedPiece = whitePiece
                                    }
                                } else if (blackPiece != null && !blackPiece.nextMove.isNullOrEmpty()) {
                                    moveAbleList.addAll(blackPiece.nextMove)
                                    selectedPiece = blackPiece
                                }
                            }, contentAlignment = Alignment.Center
                    ) {
                        if (blackPiece != null) {
                            Image(
                                painter = painterResource(id = blackPiece.type.getPieceIcon(false)), contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        if (whitePiece != null) {
                            Image(
                                painter = painterResource(id = whitePiece.type.getPieceIcon(true)), contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        if (moveAbleItem != null) {
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .background(color = Color.Black.copy(alpha = 0.4f), shape = RoundedCornerShape(20.dp))
                            )
                        }
                    }
                }
            })
    }
}