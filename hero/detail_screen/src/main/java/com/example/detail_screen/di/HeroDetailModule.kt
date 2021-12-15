package com.example.detail_screen.di

import com.example.hero_interactors.GetHeroesFromCache
import com.example.hero_interactors.HeroInteractors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeroDetailModule {
    @Provides
    @Singleton
    fun provideGetHeroFromCache(
        interactors: HeroInteractors
    ): GetHeroesFromCache {
        return interactors.getHeroesFromCache
    }
}