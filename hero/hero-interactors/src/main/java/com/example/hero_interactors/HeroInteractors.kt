package com.example.hero_interactors

import com.example.hero_datasource.cache.HeroCache
import com.example.hero_datasource.cache.HeroDatabase
import com.example.hero_datasource.network.HeroService
import com.squareup.sqldelight.db.SqlDriver

data class HeroInteractors(
    val getHeroes: GetHeroes,
    val getHeroesFromCache: GetHeroesFromCache,
    val filterHeroes: FilterHeroes
) {

    companion object Factory {
        fun build(sqlDriver: SqlDriver): HeroInteractors {
            val service = HeroService.build()
            val cache = HeroCache.build(sqlDriver)
            return HeroInteractors(
                getHeroes = GetHeroes(service, cache),
                getHeroesFromCache = GetHeroesFromCache(cache),
                filterHeroes = FilterHeroes()
            )
        }

        val schema: SqlDriver.Schema = HeroDatabase.Schema

        val dbName: String = "heroes.db"
    }
}