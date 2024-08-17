package ru.logosph.dungeon_chicken_curry.domain.local_data

import android.content.Context

interface LocalDataRepository {
    fun getRaces(context: Context, locale: String? = null): List<String>
    fun getClasses(context: Context, locale: String? = null): List<String>
    fun getRaceAndClassModel(context: Context, race: Boolean): List<RaceAndClassModel>

}