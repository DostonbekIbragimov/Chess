package uz.chess.masters.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserResponse(
    @SerialName("id")
    val id: Int?,
    @SerialName("jwt")
    val jwt: String?
)
