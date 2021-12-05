package com.example.hero_interactors

import com.example.hero_datasource.cache.HeroCache
import com.example.hero_datasource.cache.HeroDatabase
import com.example.hero_datasource.network.HeroService
import com.squareup.sqldelight.db.SqlDriver

data class HeroInteractors(
    val getHeroes: GetHeroes
) {

    companion object Factory {
        fun build(sqlDriver:SqlDriver): HeroInteractors {
            val service = HeroService.build()
            val cache = HeroCache.build(sqlDriver)
            return HeroInteractors(
                getHeroes = GetHeroes(service,cache)
            )
        }
        val schema:SqlDriver.Schema = HeroDatabase.Schema

        val dbName:String = "heroes.db"
    }
}