package ru.logosph.dungeon_chicken_curry.ui.fragments.login

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.logosph.dungeon_chicken_curry.data.user.AuthorizationRepositoryImpl
import ru.logosph.dungeon_chicken_curry.domain.LoginUseCase
import ru.logosph.dungeon_chicken_curry.domain.UserModel

class LoginViewModel : ViewModel() {

    suspend fun login(
        username: String,
        password: String,
        context: Context
    ): Flow<Boolean> = flow {
        emit(
            LoginUseCase.execute(
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