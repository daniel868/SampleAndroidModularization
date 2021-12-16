package com.example.hero_domain

import com.example.core.FilterOrder

sealed class HeroFilter(val uiValue: String) {
    //sort alphabetic or non alphabetic
    data class Hero(
        val order: FilterOrder = FilterOrder.Descending
    ) : HeroFilter("Hero")


    //sort by pro-wins aspect
    data class ProWins(
        val order: FilterOrder = FilterOrder.Descending
    ) : HeroFilter("Pro win-rate")
}