package com.example.hero_interactors

import com.example.hero_datasource.network.HeroService

data class HeroInteractors(
    val getHeroes: GetHeroes
) {

    companion object Factory {
        fun build(): HeroInteractors {
            val service = HeroService.build()
            return HeroInteractors(
                getHeroes = GetHeroes(service)
            )
        }
    }
}