package uz.chess.masters.data.api.auth

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import uz.chess.masters.data.request.LoginRequest
import uz.chess.masters.data.response.LoginResponse
import uz.chess.masters.utils.URL_LOGIN
import uz.chess.masters.utils.api.NetworkResult
import javax.inject.Inject

class AuthServiceImpl @Inject constructor(
    private val client: HttpClient
) : AuthService {
    override suspend fun login(loginRequest: LoginRequest): NetworkResult<LoginResponse> {
        return try {
            val resp = client.post<LoginResponse>(URL_LOGIN) {
                body = loginRequest
            }
            NetworkResult.Success(data = resp)
        } catch (e: Exception) {
            NetworkResult.Error(message = e.message.toString())
        }
    }

    override suspend fun register() {

    }
}