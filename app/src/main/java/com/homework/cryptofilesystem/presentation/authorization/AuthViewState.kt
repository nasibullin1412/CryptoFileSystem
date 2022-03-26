package com.homework.cryptofilesystem.presentation.authorization

sealed class AuthViewState {
    object SuccessAuth : AuthViewState()
    class ErrorAuth(val throwable: Throwable) : AuthViewState()
}
