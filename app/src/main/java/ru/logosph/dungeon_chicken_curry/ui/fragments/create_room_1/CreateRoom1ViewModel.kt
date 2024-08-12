package ru.logosph.dungeon_chicken_curry.ui.fragments.create_room_1

import android.content.Context
import androidx.lifecycle.ViewModel
import ru.logosph.dungeon_chicken_curry.domain.GetLocalRacesUseCase

class CreateRoom1ViewModel : ViewModel() {

    fun getLocalRaces(context: Context): List<String> {
        return GetLocalRacesUseCase.execute(context)
    }
}