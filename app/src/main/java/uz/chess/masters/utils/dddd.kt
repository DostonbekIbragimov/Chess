package uz.chess.masters.utils

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class TestSerialClass(
    @SerialName("id_test")
    val idTest: Long
) {
    fun toStringJson(): String {
        return Json.encodeToString(this)
    }
}
