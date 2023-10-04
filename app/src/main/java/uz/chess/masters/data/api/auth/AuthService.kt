package uz.chess.masters.data.api.auth

import uz.chess.masters.data.response.CreateUserResponse
import uz.chess.masters.data.request.CreateUserRequest
import uz.chess.masters.data.request.LoginRequest
import uz.chess.masters.data.response.LoginResponse
import uz.chess.masters.utils.api.NetworkResult

interface AuthService {
    suspend fun login(loginRequest: LoginRequest): NetworkResult<LoginResponse>
    suspend fun createUser(createUserRequest: CreateUserRequest):NetworkResult<CreateUserResponse>
}