package com.homework.cryptofilesystem.data

import com.homework.cryptofilesystem.data.prefs.SharedPrefs
import com.homework.cryptofilesystem.data.prefs.SharedPrefs.PASSWORD
import com.homework.cryptofilesystem.data.prefs.SharedPrefs.USERNAME
import com.homework.cryptofilesystem.domain.entity.AuthEntity
import com.homework.cryptofilesystem.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AuthRepositoryImpl : AuthRepository {

    override suspend fun authUser(authEntity: AuthEntity): Flow<Boolean> = flow {
        val username = SharedPrefs.getSharedPreferenceString(USERNAME)
        val password = SharedPrefs.getSharedPreferenceString(PASSWORD)
        if (username == "") {
            setAuth(authEntity)
            emit(true)
            return@flow
        }
        emit(checkAuth(authEntity, username, password = password))
    }.flowOn(Dispatchers.IO)

    override suspend fun changeAuth(authEntity: AuthEntity): Flow<Boolean> = flow {
        setAuth(authEntity)
        emit(true)
    }

    private fun checkAuth(authEntity: AuthEntity, username: String, password: String): Boolean {
        return authEntity.run { this.username == username && this.password == password }
    }

    private fun setAuth(authEntity: AuthEntity) {
        SharedPrefs.setValueToSharedPreference(
            USERNAME,
            authEntity.username
        )
        SharedPrefs.setValueToSharedPreference(
            PASSWORD,
            authEntity.password
        )
    }
}
