package com.example.hero_interactors

import com.example.core.FilterOrder
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

        when (heroFilter) {
            is HeroFilter.Hero -> {
                when (heroFilter.order) {
                    FilterOrder.Ascending -> {
                        filteredList.sortBy { it.localizedName }
                    }
                    FilterOrder.Descending -> {
                        filteredList.sortByDescending { it.localizedName }
                    }
                }
            }
            is HeroFilter.ProWins -> TODO()
        }

        return filteredList
    }
}