package com.example.modulandroidapp.ui_heroList.ui

import com.example.core.UiComponentState
import com.example.hero_domain.HeroFilter

sealed class HeroListEvents {
    object GetHeroes : HeroListEvents()

    object FilterHeroes : HeroListEvents()

    data class UpdateHeroName(
        val heroName: String
    ) : HeroListEvents()

    data class ShowFilterDialog(
        val uiComponentState: UiComponentState
    ) : HeroListEvents()

    data class UpdateHeroFilter(
        val heroFilter: HeroFilter
    ) : HeroListEvents()

}
