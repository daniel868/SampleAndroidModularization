package com.example.hero_interactors

import com.example.hero_domain.Hero
import com.example.hero_domain.HeroAttribute
import com.example.hero_domain.HeroFilter

class FilterHeroes {
    fun execute(
        current: List<Hero>,
        heroName: String,
        heroFilter: HeroFilter,
        attribute: HeroAttribute
    ): List<Hero> {
        var filteredList: MutableList<Hero> = current.filter { hero ->
            hero.localizedName.lowercase().contains(heroName.lowercase())
        }.toMutableList()

        return filteredList
    }
}