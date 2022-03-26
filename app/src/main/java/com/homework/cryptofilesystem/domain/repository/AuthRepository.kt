package com.homework.cryptofilesystem.domain.repository

import com.homework.cryptofilesystem.domain.entity.AuthEntity
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun authUser(authEntity: AuthEntity): Flow<Boolean>

    suspend fun changeAuth(authEntity: AuthEntity): Flow<Boolean>
}
