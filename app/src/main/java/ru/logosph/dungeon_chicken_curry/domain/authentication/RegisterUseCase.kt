package ru.logosph.dungeon_chicken_curry.domain.authentication

import android.content.Context

class RegisterUseCase {
    companion object {
        suspend fun execute(
            repo: AuthorizationRepository,
            user: UserModel,
            context: Context
        ): Boolean {
            return repo.register(user, context)
        }
    }
}