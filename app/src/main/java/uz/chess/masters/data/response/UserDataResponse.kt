package uz.chess.masters.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    @SerialName("id")
    val id: Int,
    @SerialName("username")
    val username: String,
    @SerialName("fullName")
    val fullName: String,
    @SerialName("avatar")
    val avatar: String? = null,
)
