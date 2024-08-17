package ru.logosph.dungeon_chicken_curry.domain.local_data

import android.content.Context

class GetLocalRacesUseCase {
    companion object {
        fun execute(
            context: Context,
            repo: LocalDataRepository
        ): List<RaceAndClassModel> {
            return repo.getRaceAndClassModel(
                context,
                race = true
            )
        }
    }
}