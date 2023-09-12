package uz.chess.masters.utils

import kotlinx.serialization.json.Json

inline fun <reified T> String.getDataFromMyServerJson(): T {
    val json = Json {
        ignoreUnknownKeys = true
    }

    return json.decodeFromString(this.substringAfter('#'))
}