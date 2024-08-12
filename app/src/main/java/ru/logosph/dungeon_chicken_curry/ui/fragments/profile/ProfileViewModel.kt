package ru.logosph.dungeon_chicken_curry.ui.fragments.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import ru.logosph.dungeon_chicken_curry.data.user.AuthorizationRepositoryImpl
import ru.logosph.dungeon_chicken_curry.domain.LogoutUseCase

class ProfileViewModel : ViewModel() {

    fun logOut(context: Context) {
        LogoutUseCase.execute(
            repo = AuthorizationRepositoryImpl(),
            context = context
        )
    }
}