package com.homework.cryptofilesystem.domain.usecase

import com.homework.cryptofilesystem.data.AuthRepositoryImpl
import com.homework.cryptofilesystem.domain.entity.AuthEntity
import com.homework.cryptofilesystem.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class ChangeAuthUseCase {

    private val authRepository: AuthRepository = AuthRepositoryImpl()

    suspend operator fun invoke(authEntity: AuthEntity): Flow<Boolean> {
        return authRepository.changeAuth(authEntity)
    }
}
