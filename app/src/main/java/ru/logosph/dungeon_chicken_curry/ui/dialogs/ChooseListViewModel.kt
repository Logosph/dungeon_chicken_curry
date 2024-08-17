package ru.logosph.dungeon_chicken_curry.ui.dialogs

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import ru.logosph.dungeon_chicken_curry.data.LocalDataRepositoryImpl
import ru.logosph.dungeon_chicken_curry.domain.local_data.GetLocalRacesUseCase
import ru.logosph.dungeon_chicken_curry.domain.local_data.RaceAndClassModel

class ChooseListViewModel : ViewModel() {

    fun getRaces(
        context: Context,
    ): List<RaceAndClassModel> = GetLocalRacesUseCase.execute(
            context,
            repo = LocalDataRepositoryImpl()
        )

}