package ru.logosph.dungeon_chicken_curry.ui.fragments.signup

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.logosph.dungeon_chicken_curry.data.user.AuthorizationRepositoryImpl
import ru.logosph.dungeon_chicken_curry.domain.RegisterUseCase
import ru.logosph.dungeon_chicken_curry.domain.UserModel


class SignupViewModel : ViewModel() {

    suspend fun signup(
        username: String,
        password: String,
        context: Context
    ): Flow<Boolean> = flow {
        emit(
            RegisterUseCase.execute(
                repo = AuthorizationRepositoryImpl(),
                user = UserModel(
                    username = username,
                    password = password
                ),
                context = context
            )
        )
    }

}