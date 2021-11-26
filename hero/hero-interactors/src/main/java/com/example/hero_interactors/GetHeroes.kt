package com.example.hero_interactors

import com.example.core.DataState
import com.example.core.Logger
import com.example.core.ProgressBarState
import com.example.core.UiComponent
import com.example.hero_datasource.network.HeroService
import com.example.hero_domain.Hero
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetHeroes(
    private val service: HeroService,
    //TODO: add caching
) {
    fun execute(): Flow<DataState<List<Hero>>> = flow {
        try {
            emit(DataState.Loading<List<Hero>>(progressBarState = ProgressBarState.Loading))

            delay(3000)

            val heroes: List<Hero> = try {
                service.getHeroStats()
            } catch (e: Exception) {
                e.printStackTrace()

                DataState.Response<List<Hero>>(
                    uiComponent = UiComponent.Dialog(
                        "Network Data Error",
                        description = e.message ?: "Unknown"
                    )
                )

                //return an empty list
                listOf()
            }

            //TODO: Caching

            emit(DataState.Data(data = heroes))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(
                DataState.Response<List<Hero>>(
                    uiComponent = UiComponent.Dialog("Error", e.message ?: "Unknown error")
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}