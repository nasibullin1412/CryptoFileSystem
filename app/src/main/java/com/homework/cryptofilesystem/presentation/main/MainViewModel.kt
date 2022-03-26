package com.homework.cryptofilesystem.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.cryptofilesystem.domain.entity.AuthEntity
import com.homework.cryptofilesystem.domain.usecase.ChangeAuthUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val changeAuthUseCase: ChangeAuthUseCase = ChangeAuthUseCase()

    fun changeAuth(authEntity: AuthEntity) {
        viewModelScope.launch {
            changeAuthUseCase(authEntity).collect()
        }
    }
}
