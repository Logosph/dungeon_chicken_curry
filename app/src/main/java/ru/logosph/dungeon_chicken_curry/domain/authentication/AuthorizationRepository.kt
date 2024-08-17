package ru.logosph.dungeon_chicken_curry.domain.authentication

import android.content.Context

interface AuthorizationRepository {
    suspend fun login(user: UserModel, context: Context): Boolean
    suspend fun loginByToken(context: Context): Boolean
    fun logout(context: Context): Boolean
    suspend fun register(user: UserModel, context: Context): Boolean
}