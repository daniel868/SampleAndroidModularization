package com.example.modulandroidapp.ui_heroList.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import coil.ImageLoader
import com.example.modulandroidapp.ui_heroList.components.DefaultScreenUi
import com.example.core.UiComponentState
import com.example.modulandroidapp.ui_heroList.components.HeroListFilter
import com.example.modulandroidapp.ui_heroList.components.HeroListItem
import com.example.modulandroidapp.ui_heroList.components.HeroListToolbar

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun HeroList(
    state: HeroListState,
    events: (HeroListEvents) -> Unit,
    imageLoader: ImageLoader,
    navigateToHeroDetailScreen: (Int) -> Unit
) {

    DefaultScreenUi(progressBarState = state.progressBarState) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                HeroListToolbar(heroName = state.heroName,
                    onHeroNameChange = {
                        events(HeroListEvents.UpdateHeroName(it))
                    },
                    onExecuteSearch = {
                        events(HeroListEvents.FilterHeroes)
                    },
                    onShowFilterDialog = {
                        events(HeroListEvents.ShowFilterDialog(UiComponentState.Show))
                    })
                LazyColumn {
                    items(state.filterHeroes) {
                        HeroListItem(hero = it, onSelectHero = { heroId ->
                            navigateToHeroDetailScreen(heroId)
                        }, imageLoader = imageLoader)
                    }
                }
            }
            if (state.filterDialogState is UiComponentState.Show) {
                HeroListFilter(
                    heroFilter = state.heroFilter,
                    onUpdateHeroFilter = { heroFilter ->
                        //add new events that handle this filter
                        events(HeroListEvents.UpdateHeroFilter(heroFilter = heroFilter))
                    },
                    onCloseDialog = {
                        events(HeroListEvents.ShowFilterDialog(UiComponentState.Hide))
                    }
                )
            }
        }
    }


}