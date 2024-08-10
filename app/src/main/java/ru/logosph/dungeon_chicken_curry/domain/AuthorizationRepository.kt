package ru.logosph.dungeon_chicken_curry.domain

import android.content.Context

interface AuthorizationRepository {
    suspend fun login(user: UserModel, context: Context): Boolean
    suspend fun loginByToken(context: Context): Boolean
    suspend fun logout(context: Context): Boolean
    suspend fun register(user: UserModel, context: Context): Boolean
}