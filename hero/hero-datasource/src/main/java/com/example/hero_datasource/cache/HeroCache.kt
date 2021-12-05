package com.example.hero_datasource.cache

import com.example.hero_domain.Hero
import com.squareup.sqldelight.db.SqlDriver

interface HeroCache {

    suspend fun getHero(id: Int): Hero?

    suspend fun removeHero(id: Int)

    suspend fun selectAll(): List<Hero>

    suspend fun insertHero(hero: Hero)

    suspend fun insert(heroes: List<Hero>)

    suspend fun searchByName(localizedName: String): List<Hero>

    suspend fun searchByAttr(primaryAttr: String): List<Hero>

    suspend fun searchByAttackType(attackType: String): List<Hero>

    suspend fun searchByRole(
        carry: Boolean = false,
        escape: Boolean = false,
        nuker: Boolean = false,
        initiator: Boolean = false,
        durable: Boolean = false,
        disabler: Boolean = false,
        jungler: Boolean = false,
        support: Boolean = false,
        pusher: Boolean = false
    ): List<Hero>

    companion object Factory {
        fun build(sqlDriver: SqlDriver): HeroCache {
            return HeroCacheImpl(HeroDatabase(sqlDriver))
        }

    }

}