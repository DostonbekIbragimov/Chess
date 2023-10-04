package uz.chess.masters.data.api.main

import uz.chess.masters.data.response.UserData
import uz.chess.masters.utils.api.NetworkResult

interface MainService {
    suspend fun getAllUsers():NetworkResult<List<UserData>>

}