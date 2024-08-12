package ru.logosph.dungeon_chicken_curry.ui.fragments.login

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.logosph.dungeon_chicken_curry.data.user.AuthorizationRepositoryImpl
import ru.logosph.dungeon_chicken_curry.domain.LoginByTokenUseCase
import ru.logosph.dungeon_chicken_curry.domain.LoginUseCase
import ru.logosph.dungeon_chicken_curry.domain.UserModel
import ru.logosph.dungeon_chicken_curry.ui.fragments.LoadingStates

class LoginViewModel : ViewModel() {

    suspend fun login(
        username: String,
        password: String,
        context: Context
    ): Flow<LoadingStates> = flow {
        emit(LoadingStates.LOADING)
        try {
            val result = LoginUseCase.execute(
                repo = AuthorizationRepositoryImpl(),
                user = UserModel(
                    username = username,
                    password = password
                ),
                context = context
            )
            if (result) {
                emit(LoadingStates.SUCCESS)
            } else {
                emit(LoadingStates.ERROR)
            }
        } catch (e: Exception) {
            emit(LoadingStates.ERROR)
        }

    }

    suspend fun loginWithToken(context: Context): Flow<LoadingStates> = flow {
        emit(LoadingStates.LOADING)
        try {
            val result = LoginByTokenUseCase.execute(
                repo = AuthorizationRepositoryImpl(),
                context = context
            )
            if (result) {
                emit(LoadingStates.SUCCESS)
            } else {
                emit(LoadingStates.ERROR)
            }
        } catch (e: Exception) {
            emit(LoadingStates.ERROR)
        }
    }

}