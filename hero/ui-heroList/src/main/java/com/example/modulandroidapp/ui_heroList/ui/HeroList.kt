package com.example.modulandroidapp.ui_heroList.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import coil.ImageLoader
import com.example.core.ProgressBarState
import com.example.modulandroidapp.ui_heroList.components.HeroListItem
import com.example.modulandroidapp.ui_heroList.components.HeroListToolbar
import com.example.modulandroidapp.ui_heroList.ui.HeroListState

@ExperimentalComposeUiApi
@Composable
fun HeroList(
    state: HeroListState,
    events: (HeroListEvents) -> Unit,
    imageLoader: ImageLoader,
    navigateToHeroDetailScreen: (Int) -> Unit
) {
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
                })
            LazyColumn {
                items(state.filterHeroes) {
                    //Text(text = it.localizedName)
                    HeroListItem(hero = it, onSelectHero = { heroId ->
                        navigateToHeroDetailScreen(heroId)
                    }, imageLoader = imageLoader)
                }
            }
        }
        if (state.progressBarState is ProgressBarState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}