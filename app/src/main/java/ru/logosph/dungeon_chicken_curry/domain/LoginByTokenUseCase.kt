package ru.logosph.dungeon_chicken_curry.domain

import android.content.Context

class LoginByTokenUseCase {
    companion object {
        suspend fun execute(
            repo: AuthorizationRepository,
            context: Context
        ): Boolean {
            return repo.loginByToken(context)
        }
    }
}