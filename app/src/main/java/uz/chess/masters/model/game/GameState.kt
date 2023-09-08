package uz.chess.masters.model.game

import uz.chess.masters.model.player.Player

/**
 * Created by DostonbekIbragimov on 08/09/2023.
 */
sealed class GameState {
    object ReadyForStart : GameState()
    object Started : GameState()
    data class GameOver(val whoWin: Player) : GameState()
}