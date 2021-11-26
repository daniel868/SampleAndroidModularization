package com.example.modularandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.core.DataState
import com.example.core.Logger
import com.example.core.ProgressBarState
import com.example.core.UiComponent
import com.example.hero_domain.Hero
import com.example.hero_interactors.HeroInteractors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {

    private val heroes: MutableState<List<Hero>> = mutableStateOf(listOf())
    private val progressBar: MutableState<ProgressBarState> = mutableStateOf(ProgressBarState.Idle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val interactors = HeroInteractors.build().getHeroes
        val logger = Logger("GetHeroTest")

        interactors.execute().onEach { dataState ->
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
                    heroes.value = dataState.data ?: listOf()
                }
                is DataState.Loading -> {
                    progressBar.value = dataState.progressBarState
                }
            }
        }.launchIn(CoroutineScope(IO))

        setContent {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn {
                    items(heroes.value) {
                        Text(text = it.localizedName)
                    }
                }
                if (progressBar.value is ProgressBarState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}