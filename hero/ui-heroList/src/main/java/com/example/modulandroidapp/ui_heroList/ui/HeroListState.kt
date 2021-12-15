package com.example.modulandroidapp.ui_heroList.ui

import com.example.core.ProgressBarState
import com.example.hero_domain.Hero

data class HeroListState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val heroes: List<Hero> = listOf(),
    val filterHeroes: List<Hero> = listOf(),
    val heroName: String = ""
)
