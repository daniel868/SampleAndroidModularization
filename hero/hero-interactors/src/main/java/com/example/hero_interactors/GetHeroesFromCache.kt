package com.example.hero_interactors

import com.example.core.DataState
import com.example.core.ProgressBarState
import com.example.core.UiComponent
import com.example.hero_datasource.cache.HeroCache
import com.example.hero_domain.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetHeroesFromCache(
    private val cache: HeroCache
) {
    fun execute(
        id: Int
    ): Flow<DataState<Hero>> = flow {
        emit(DataState.Loading(progressBarState = ProgressBarState.Loading))

        try {
            val cachedHero =
                cache.getHero(id) ?: throw Exception("Hero not found in the local database");

            emit(DataState.Data(data = cachedHero))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(
                DataState.Response<Hero>(
                    uiComponent = UiComponent.Dialog(
                        title = "Error",
                        description = e.message ?: "Unknown error"
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}