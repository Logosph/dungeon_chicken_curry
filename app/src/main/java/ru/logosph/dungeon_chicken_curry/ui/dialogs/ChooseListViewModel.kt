package ru.logosph.dungeon_chicken_curry.ui.dialogs

import android.content.Context
import androidx.lifecycle.ViewModel
import ru.logosph.dungeon_chicken_curry.data.LocalDataRepositoryImpl
import ru.logosph.dungeon_chicken_curry.domain.local_data.GetLocalClassesUseCase
import ru.logosph.dungeon_chicken_curry.domain.local_data.GetLocalRacesUseCase
import ru.logosph.dungeon_chicken_curry.domain.local_data.RaceAndClassModel

class ChooseListViewModel : ViewModel() {

    val selectedItems = mutableSetOf<RaceAndClassModel>()
    var isButtonAllowed = false
    val racesAndClasses: MutableList<RaceAndClassModel> = mutableListOf()

    fun toggleButtonState() {
        isButtonAllowed = !isButtonAllowed
    }

    fun toggleSelection(position: Int) {
        with(selectedItems) {
            if (!contains(racesAndClasses[position])) add(racesAndClasses[position]) else remove(racesAndClasses[position])
        }
    }

    fun getRaces(
        context: Context,
    ): List<RaceAndClassModel> {
        racesAndClasses += GetLocalRacesUseCase.execute(
            context,
            repo = LocalDataRepositoryImpl()
        )

        return racesAndClasses
    }

    fun getClasses(
        context: Context
    ): List<RaceAndClassModel> {
        racesAndClasses += GetLocalClassesUseCase.execute(
            context,
            repo = LocalDataRepositoryImpl()
        )

        return racesAndClasses
    }

}