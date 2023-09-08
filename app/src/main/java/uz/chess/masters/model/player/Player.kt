package uz.chess.masters.model.player

import uz.chess.masters.model.piece.Piece

/**
 * Created by DostonbekIbragimov on 08/09/2023.
 */
data class Player(
    var leftTime: Long,
    var currentPieces: List<Piece>,
    var takenPieces: List<Piece>,
    var isMoveQueue: Boolean
)