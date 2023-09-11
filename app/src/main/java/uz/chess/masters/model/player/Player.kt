package uz.chess.masters.model.player

import uz.chess.masters.model.piece.Piece

/**
 * Created by DostonbekIbragimov on 08/09/2023.
 */
data class Player(
    var leftTime: Long,
    var currentPieces: ArrayList<Piece>,
    var takenPieces: ArrayList<Piece>,
    var isMoveQueue: Boolean
)