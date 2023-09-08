package uz.chess.masters.model.game

import uz.chess.masters.model.player.Player

/**
 * Created by DostonbekIbragimov on 08/09/2023.
 */
data class Game(
    var whitePlayer: Player,
    var blackPlayer: Player,
    var gameState: GameState
)