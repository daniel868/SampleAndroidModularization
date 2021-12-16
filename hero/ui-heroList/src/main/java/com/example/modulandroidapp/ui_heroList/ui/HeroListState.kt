package com.example.modulandroidapp.ui_heroList.ui

import com.example.core.FilterOrder
import com.example.core.ProgressBarState
import com.example.core.UiComponentState
import com.example.hero_domain.Hero
import com.example.hero_domain.HeroFilter

data class HeroListState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val heroes: List<Hero> = listOf(),
    val filterHeroes: List<Hero> = listOf(),
    val heroName: String = "",
    val filterDialogState: UiComponentState = UiComponentState.Hide, //show hide filter dialog
    val heroFilter: HeroFilter = HeroFilter.Hero(FilterOrder.Descending) //how to filter the hero
)
