package uz.chess.masters.ui.screens.game

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import uz.chess.masters.data.models.Piece
import uz.chess.masters.data.models.PieceType
import uz.chess.masters.data.models.Position
import uz.chess.masters.data.models.getPieceIcon
import uz.chess.masters.utils.FIRST_PLAYER_COLOR
import uz.chess.masters.utils.FIRST_PLAYER_ID
import uz.chess.masters.utils.SECOND_PLAYER_COLOR
import uz.chess.masters.utils.extensions.getCellColor
import uz.chess.masters.utils.showToast

/**
 * Created by DostonbekIbragimov on 08/09/2023.
 */
@SuppressLint("UnrememberedMutableState")
@Composable
fun GameScreen(
    navController: NavController,
    viewModel: GameViewModel = hiltViewModel()
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp

    val moveAbleList = mutableStateListOf<Position>()
    val squareWidth = screenWidth / 8

    val blackPlayerTakenPieces = mutableStateListOf<Piece>()
    val whitePlayerTakenPieces = mutableStateListOf<Piece>()

    var selectedPiece: Piece? = null

    var isWhitePlayer = true

    val game by remember {
        viewModel.gameState
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit, block = {
        viewModel.connectSocket()
    })

    LaunchedEffect(key1 = Unit, block = {
        viewModel.errorState.collect {
            showToast(context, it)
        }
    })

    fun boardClicked(x: Int, y: Int, blackPiece: Piece?, whitePiece: Piece?) {
        moveAbleList.clear()
        var isNotTakenWhitePiece = true
        var isNotTakenBlackPiece = true

        /** Take piece or just move **/
        if (selectedPiece != null) {
            val newSelectedCell = selectedPiece!!.nextMove.find { it.x == x && it.y == y }
            if (newSelectedCell != null) {
                if (isWhitePlayer) {
                    game.whitePlayer?.currentPieces?.find { it.position == selectedPiece!!.position }?.position = newSelectedCell
                    game.whitePlayer?.currentPieces?.find { it.position == selectedPiece!!.position }?.nextMove = arrayListOf()

                    val hasBlackPieceInNewSelectedCell = game.blackPlayer?.currentPieces?.find { it.position.x == newSelectedCell.x && it.position.y == newSelectedCell.y }
                    if (hasBlackPieceInNewSelectedCell != null) {
                        game.blackPlayer?.currentPieces?.removeIf {
                            it.type == hasBlackPieceInNewSelectedCell.type &&
                                    it.position.x == hasBlackPieceInNewSelectedCell.position.x &&
                                    it.position.y == hasBlackPieceInNewSelectedCell.position.y
                        }
                        game.whitePlayer?.takenPieces?.apply { add(hasBlackPieceInNewSelectedCell) }
                        whitePlayerTakenPieces.add(hasBlackPieceInNewSelectedCell)
                        isNotTakenBlackPiece = false
                    }

                } else {
                    game.blackPlayer?.currentPieces?.find { it.position == selectedPiece!!.position }?.position = newSelectedCell
                    game.blackPlayer?.currentPieces?.find { it.position == selectedPiece!!.position }?.nextMove = arrayListOf()

                    val hasWhitePieceInNewSelectedCell = game.whitePlayer?.currentPieces?.find { it.position.x == newSelectedCell.x && it.position.y == newSelectedCell.y }
                    if (hasWhitePieceInNewSelectedCell != null) {
                        game.whitePlayer?.currentPieces?.removeIf {
                            it.type == hasWhitePieceInNewSelectedCell.type &&
                                    it.position.x == hasWhitePieceInNewSelectedCell.position.x &&
                                    it.position.y == hasWhitePieceInNewSelectedCell.position.y
                        }
                        game.blackPlayer?.takenPieces?.apply { add(hasWhitePieceInNewSelectedCell) }
                        blackPlayerTakenPieces.add(hasWhitePieceInNewSelectedCell)
                        isNotTakenWhitePiece = false
                    }
                }
                viewModel.moveSend(game)
                isWhitePlayer = !isWhitePlayer
            }
        }

        if (isWhitePlayer) {
            if (whitePiece != null && whitePiece.nextMove.isNotEmpty() && isNotTakenWhitePiece) {
                moveAbleList.addAll(whitePiece.nextMove)
                selectedPiece = whitePiece
            }
            if (whitePiece != null && isNotTakenWhitePiece) {
                moveAbleList.clear()

                val nextMoveAbleList = when (whitePiece.type) {
                    PieceType.ROOK -> getRookMoves(whitePiece, getBoard(game))
                    PieceType.KNIGHT -> getKnightMoves(whitePiece, getBoard(game))
                    PieceType.BISHOP -> getBishopMoves(whitePiece, getBoard(game))
                    PieceType.QUEEN -> getQueenMoves(whitePiece, getBoard(game))
                    PieceType.KING -> getKingMoves(whitePiece, getBoard(game))
                    PieceType.PAWN -> getPawnMoves(whitePiece, getBoard(game))
                    else -> whitePiece.nextMove
                }

                moveAbleList.addAll(nextMoveAbleList)
                game.whitePlayer?.currentPieces?.find { it.position.x == x && it.position.y == y }?.apply {
                    nextMove = arrayListOf()
                    nextMove.addAll(nextMoveAbleList)
                }
                selectedPiece = whitePiece
            }
        } else {
            if (blackPiece != null && blackPiece.nextMove.isNotEmpty() && isNotTakenBlackPiece) {
                moveAbleList.addAll(blackPiece.nextMove)
                selectedPiece = blackPiece
            }
            if (blackPiece != null && isNotTakenBlackPiece) {
                moveAbleList.clear()

                val nextMoveAbleList = when (blackPiece.type) {
                    PieceType.ROOK -> getRookMoves(blackPiece, getBoard(game))
                    PieceType.KNIGHT -> getKnightMoves(blackPiece, getBoard(game))
                    PieceType.BISHOP -> getBishopMoves(blackPiece, getBoard(game))
                    PieceType.QUEEN -> getQueenMoves(blackPiece, getBoard(game))
                    PieceType.KING -> getKingMoves(blackPiece, getBoard(game))
                    PieceType.PAWN -> getPawnMoves(blackPiece, getBoard(game))
                    else -> blackPiece.nextMove
                }

                moveAbleList.addAll(nextMoveAbleList)
                game.blackPlayer?.currentPieces?.find { it.position.x == x && it.position.y == y }?.apply {
                    nextMove = arrayListOf()
                    nextMove.addAll(nextMoveAbleList)
                }
                selectedPiece = blackPiece
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {

        Text(text = "You are ${if (viewModel.playerState.value?.id == FIRST_PLAYER_ID) FIRST_PLAYER_COLOR else SECOND_PLAYER_COLOR} player", Modifier.padding(20.dp))

        LazyRow(contentPadding = PaddingValues(horizontal = 10.dp)) {
            items(blackPlayerTakenPieces.size) { index ->
                Image(
                    painter = painterResource(id = blackPlayerTakenPieces[index].type.getPieceIcon(true)), contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        LazyVerticalGrid(columns = GridCells.Fixed(8), contentPadding = PaddingValues(vertical = 10.dp),
            content = {
                items(64) { index ->
                    val x = index % 8
                    val y = index / 8
                    val blackPiece = game.blackPlayer?.currentPieces?.find { it.position.x == x && it.position.y == y }
                    val whitePiece = game.whitePlayer?.currentPieces?.find { it.position.x == x && it.position.y == y }
                    val moveAbleItem = moveAbleList.find { it.x == x && it.y == y }

                    Box(
                        modifier = Modifier
                            .size(squareWidth.dp)
                            .background(color = index.getCellColor())
                            .clickable(interactionSource = remember { MutableInteractionSource() }, indication = rememberRipple()) {
                                if (viewModel.isMyQueue()) {
                                    boardClicked(x, y, blackPiece, whitePiece)
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
                                    .size(squareWidth.dp)
                                    .background(color = Color.Green.copy(alpha = 0.2f)/*, shape = RoundedCornerShape(20.dp)*/)
                            )
                        }
                    }
                }
            })

        LazyRow(contentPadding = PaddingValues(horizontal = 10.dp)) {
            items(whitePlayerTakenPieces.size) { index ->
                Image(
                    painter = painterResource(id = whitePlayerTakenPieces[index].type.getPieceIcon(false)), contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}