package com.example.modulandroidapp.ui_heroList.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.DataState
import com.example.core.FilterOrder
import com.example.core.Logger
import com.example.core.UiComponent
import com.example.hero_domain.HeroAttribute
import com.example.hero_domain.HeroFilter
import com.example.hero_interactors.FilterHeroes
import com.example.hero_interactors.GetHeroes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HeroListViewModel @Inject constructor(
    private val getHeroes: GetHeroes,
    private val filterHeroes: FilterHeroes,
    private val savedStateHandle: SavedStateHandle,
    @Named("heroListLogger") private val logger: Logger
) : ViewModel() {
    val state: MutableState<HeroListState> = mutableStateOf(HeroListState())

    init {
        onTriggerEvent(HeroListEvents.GetHeroes)
    }

    fun onTriggerEvent(event: HeroListEvents) {
        when (event) {
            is HeroListEvents.GetHeroes -> {
                getHeroes()
            }
            is HeroListEvents.UpdateHeroName -> {
                updateHeroName(event.heroName)
            }
            HeroListEvents.FilterHeroes -> {
                filterHeroes()
            }
            is HeroListEvents.ShowFilterDialog -> {
                state.value = state.value.copy(filterDialogState = event.uiComponentState)
            }
            is HeroListEvents.UpdateHeroFilter -> {
                updateHeroFilter(event.heroFilter)
            }
        }
    }

    private fun getHeroes() {
        getHeroes.execute().onEach { dataState ->
            when (dataState) {
                is DataState.Response -> {
                    when (dataState.uiComponent) {
                        is UiComponent.Dialog -> {
                            logger.log(((dataState.uiComponent as UiComponent.Dialog).description))
                        }
                        is UiComponent.None -> {
                            logger.log(((dataState.uiComponent as UiComponent.None).message))
                        }
                    }
                }
                is DataState.Data -> {
                    state.value = state.value.copy(heroes = dataState.data ?: listOf())
                    state.value = state.value.copy(filterHeroes = dataState.data ?: listOf())
                }
                is DataState.Loading -> {
                    state.value = state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun updateHeroName(heroName: String) {
        state.value = state.value.copy(heroName = heroName)
    }

    private fun filterHeroes() {
        val filteredList = filterHeroes.execute(
            current = state.value.heroes,
            heroName = state.value.heroName,
            heroFilter = state.value.heroFilter,
            attribute = HeroAttribute.Unknown
        )
        state.value = state.value.copy(filterHeroes = filteredList)
    }

    private fun updateHeroFilter(heroFilter: HeroFilter) {
        state.value = state.value.copy(heroFilter = heroFilter)
        filterHeroes()
    }

}