package uz.chess.masters.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("userId")
    val userId: String? = null,
    @SerialName("jwt")
    val jwt: String? = null
)
