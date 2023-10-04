package uz.chess.masters.data.api.main

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import uz.chess.masters.data.response.UserData
import uz.chess.masters.utils.URL_ALL_USERS
import uz.chess.masters.utils.api.NetworkResult

class MainServiceImpl(
    private val client: HttpClient
) : MainService {
    override suspend fun getAllUsers(): NetworkResult<List<UserData>> {
        return try {
            val resp = client.get<List<UserData>>(URL_ALL_USERS)
            NetworkResult.Success(data = resp)
        } catch (e: Exception) {
            NetworkResult.Error(message = e.message.toString())
        }
    }

}