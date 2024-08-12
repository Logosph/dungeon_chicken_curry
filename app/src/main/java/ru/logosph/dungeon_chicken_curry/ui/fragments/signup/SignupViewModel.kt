package ru.logosph.dungeon_chicken_curry.ui.fragments.signup

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.logosph.dungeon_chicken_curry.data.user.AuthorizationRepositoryImpl
import ru.logosph.dungeon_chicken_curry.domain.RegisterUseCase
import ru.logosph.dungeon_chicken_curry.domain.UserModel
import ru.logosph.dungeon_chicken_curry.ui.fragments.LoadingStates


class SignupViewModel : ViewModel() {

    suspend fun signup(
        username: String,
        password: String,
        context: Context
    ): Flow<LoadingStates> = flow {
        emit(LoadingStates.LOADING)
        try {
            emit(
                if (RegisterUseCase.execute(
                        repo = AuthorizationRepositoryImpl(),
                        user = UserModel(
                            username = username,
                            password = password
                        ),
                        context = context
                    )
                )
                    LoadingStates.SUCCESS
                else
                    LoadingStates.ERROR
            )
        } catch (e: Exception) {
            emit(LoadingStates.ERROR)
        }
    }

}