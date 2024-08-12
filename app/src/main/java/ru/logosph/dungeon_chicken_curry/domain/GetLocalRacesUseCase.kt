package ru.logosph.dungeon_chicken_curry.domain

import android.content.Context

class GetLocalRacesUseCase {
    companion object {
        fun execute(context: Context): List<String> {
            val assetManager = context.assets

            try {
                val inputStream = assetManager.open("races.txt")
                val races = inputStream.bufferedReader().readLines()
                inputStream.close()
                return races
            } catch (e: Exception) {
                e.printStackTrace()
                return emptyList()
            }
        }
    }
}