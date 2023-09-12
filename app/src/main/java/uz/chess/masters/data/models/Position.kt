package uz.chess.masters.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Position(
    val x: Int,
    var y: Int
)