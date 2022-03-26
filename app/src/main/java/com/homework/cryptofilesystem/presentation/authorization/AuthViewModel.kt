package com.homework.cryptofilesystem.presentation.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.cryptofilesystem.domain.entity.AuthEntity
import com.homework.cryptofilesystem.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    val authViewState: LiveData<AuthViewState> get() = _authViewState
    private val _authViewState = MutableLiveData<AuthViewState>()

    private val authUseCase = AuthUseCase()

    fun doAuth(authEntity: AuthEntity) = viewModelScope.launch {
        authUseCase(authEntity).catch { error ->
            _authViewState.value = AuthViewState.ErrorAuth(error)
        }.collect {
            if (it) {
                _authViewState.value = AuthViewState.SuccessAuth
            } else {
                _authViewState.value =
                    AuthViewState.ErrorAuth(IllegalArgumentException("auth error"))
            }
        }
    }
}
