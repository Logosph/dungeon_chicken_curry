package ru.logosph.dungeon_chicken_curry.data

import android.content.Context
import android.graphics.BitmapFactory
import ru.logosph.dungeon_chicken_curry.domain.local_data.LocalDataRepository
import ru.logosph.dungeon_chicken_curry.domain.local_data.RaceAndClassModel

class LocalDataRepositoryImpl : LocalDataRepository {

    override fun getRaces(context: Context, locale: String?): List<String> {
        val locale = locale ?: context.resources.configuration.locale.language
        val assetManager = context.assets
        val inputStream = assetManager.open("$locale/races.txt")
        val races = inputStream.bufferedReader().readLines()
        inputStream.close()
        return races
    }

    override fun getClasses(context: Context, locale: String?): List<String> {
        val locale = locale ?: context.resources.configuration.locale.language
        val assetManager = context.assets
        val inputStream = assetManager.open("$locale/classes.txt")
        val classes = inputStream.bufferedReader().readLines()
        inputStream.close()
        return classes
    }

     override fun getRaceAndClassModel(context: Context, race: Boolean): List<RaceAndClassModel> {
        val assetManager = context.assets

        val enList: List<String>
        val localedList: List<String>

        if (race) {
            enList = getRaces(context, "en")
            localedList = getRaces(context)
        } else {
            enList = getClasses(context, "en")
            localedList = getClasses(context)
        }

        return List(enList.size) { index ->
            {
                val inputStream = assetManager.open("${enList[index]}.png")
                val image = BitmapFactory.decodeStream(inputStream)
                RaceAndClassModel(
                    name = localedList[index],
                    image = image
                )
            }
        }.map { it() }
    }
}