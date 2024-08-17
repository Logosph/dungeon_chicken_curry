package ru.logosph.dungeon_chicken_curry.domain.authentication

import android.content.Context

class LogoutUseCase {
    companion object {
        fun execute(
            repo: AuthorizationRepository,
            context: Context
        ): Boolean {
            return repo.logout(context)
        }
    }
}