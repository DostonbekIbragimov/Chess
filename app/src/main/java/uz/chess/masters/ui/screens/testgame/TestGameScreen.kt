package uz.chess.masters.ui.screens.testgame

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collect
import uz.chess.masters.data.models.Game
import uz.chess.masters.data.models.Piece
import uz.chess.masters.data.models.Position
import uz.chess.masters.data.models.getPieceIcon
import uz.chess.masters.utils.FIRST_PLAYER_ID
import uz.chess.masters.utils.extensions.getCellColor
import uz.chess.masters.utils.showToast

/**
 * Created by DostonbekIbragimov on 08/09/2023.
 */
@SuppressLint("UnrememberedMutableState")
@Composable
fun TestGameScreen(
    navController: NavController,
    viewModel: GameViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit, block = {
        viewModel.connectSocket()
    })

    LaunchedEffect(key1 = Unit, block = {
        viewModel.errorState.collect {
            showToast(context, it)
        }
    })

    val screenWidth = LocalConfiguration.current.screenWidthDp

    val moveAbleList = mutableStateListOf<Position>()
    val squareWidth = screenWidth / 8

    val blackPieces = viewModel.gameState.value.blackPlayer?.currentPieces ?: listOf()

    val whitePieces = viewModel.gameState.value.whitePlayer?.currentPieces ?: listOf()

    var selectedPiece: Piece? = null

    var isWhitePlayer = true
    if (viewModel.waitingState.value) {
        WaitingView()
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column {

                Text(text = "You are : ${if (viewModel.playerState.value?.id == FIRST_PLAYER_ID) "White" else "Black"} player")
                Spacer(modifier = Modifier.size(24.dp))
                Text(text = if (viewModel.waitingState.value) "Waiting for 2 player..." else "")

                Spacer(modifier = Modifier.size(48.dp))

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
                                        if (selectedPiece == null) {
                                            selectedPiece = blackPiece
                                        } else {
                                            if (whitePiece == null && blackPiece == null) {
                                                blackPieces.forEachIndexed { index, piece ->
                                                    if (selectedPiece?.position == piece.position) {
                                                        viewModel.gameState.value.apply {
                                                            val new = mutableListOf<Piece>()
                                                            this.blackPlayer?.currentPieces?.let { new.addAll(it) }
                                                            new[index] = selectedPiece!!

                                                            viewModel.gameState.value = this.copy(this.blackPlayer?.copy(currentPieces = new.toList()))
                                                        }
                                                        selectedPiece = null
                                                    }
                                                }
                                                selectedPiece = selectedPiece?.copy(position = Position(x, y))
                                            }
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
    }
}

@Composable
fun WaitingView() {
    Box(modifier = Modifier.background(Color.Green), contentAlignment = Alignment.Center)
    {
        Text(
            text = "Waiting for player 2...",
            color = Color.White, fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}