package com.example.detail_screen.ui

import androidx.lifecycle.ViewModel
import com.example.hero_interactors.GetHeroesFromCache
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeroDetailViewModel @Inject constructor(
    private val getHeroesFromCache: GetHeroesFromCache
) : ViewModel() {
    
}