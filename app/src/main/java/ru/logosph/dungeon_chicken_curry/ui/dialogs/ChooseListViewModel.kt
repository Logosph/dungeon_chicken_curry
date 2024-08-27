package ru.logosph.dungeon_chicken_curry.ui.dialogs

import android.content.Context
import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import ru.logosph.dungeon_chicken_curry.data.LocalDataRepositoryImpl
import ru.logosph.dungeon_chicken_curry.domain.local_data.GetLocalRacesUseCase
import ru.logosph.dungeon_chicken_curry.domain.local_data.RaceAndClassModel

class ChooseListViewModel : ViewModel() {

    val selectedItems = MutableLiveData<Set<Int>>(setOf())
    var buttonState = MutableLiveData<ButtonState>(ButtonState.Allowed)

    enum class ButtonState(val text: String, val color: Int) {
        Allowed("Allowed", Color.GREEN),
        Restricted("Restricted", Color.RED)
    }

    fun toggleButtonState() {
        buttonState.value = when (buttonState.value) {
            ButtonState.Allowed -> ButtonState.Restricted
            ButtonState.Restricted -> ButtonState.Allowed
            null -> ButtonState.Allowed
        }
    }

    fun toggleSelection(position: Int) {
        val currentSelections = selectedItems.value?.toMutableSet() ?: mutableSetOf()
        if (currentSelections.contains(position)) {
            currentSelections.remove(position)
        } else {
            currentSelections.add(position)
        }
        selectedItems.value = currentSelections
    }

    fun getSelectedItems(): Set<Int>? {
        return selectedItems.value
    }

    fun getRaces(
        context: Context,
    ): List<RaceAndClassModel> = GetLocalRacesUseCase.execute(
            context,
            repo = LocalDataRepositoryImpl()
        )

    fun getClasses(
        context: Context
    ): List<RaceAndClassModel> = GetLocalRacesUseCase.execute(
        context,
        repo = LocalDataRepositoryImpl()
    )

}