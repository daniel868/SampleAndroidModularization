package com.example.modulandroidapp.ui_heroList.di

import com.example.core.Logger
import com.example.hero_interactors.FilterHeroes
import com.example.hero_interactors.GetHeroes
import com.example.hero_interactors.HeroInteractors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeroListModule {
    @Provides
    @Singleton
    fun provideGetHeroes(
        interactors: HeroInteractors
    ): GetHeroes {
        return interactors.getHeroes
    }

    @Provides
    @Singleton
    fun provideHeroListFilter(
        interactors: HeroInteractors
    ): FilterHeroes {
        return interactors.filterHeroes
    }

    @Singleton
    @Provides
    @Named("heroListLogger")
    fun provideLogger(): Logger {
        return Logger(
            "heroList",
            true
        )

    }
}