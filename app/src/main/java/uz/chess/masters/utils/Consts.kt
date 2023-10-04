package uz.chess.masters.utils


const val FIRST_PLAYER_ID = "1"
const val FIRST_PLAYER_COLOR = "White"
const val SECOND_PLAYER_COLOR = "Black"
const val SECOND_PLAYER_ID = "2"

/**
Action types for Websocket
 */
const val TYPE_CONNECT = "type_connect"
const val TYPE_GAME = "type_game"
const val TYPE_MOVE = "type_move"
const val TYPE_ERROR = "type_error"


/***HTTP URLs*/
const val URL_LOGIN = "http://10.10.255.241:8080/login"
const val URL_CREATE = "http://10.10.255.241:8080/create"
const val URL_ALL_USERS = "http://10.10.255.241:8080/all-users"
const val URL_PROFILE = "http://10.10.255.241:8080/profile"