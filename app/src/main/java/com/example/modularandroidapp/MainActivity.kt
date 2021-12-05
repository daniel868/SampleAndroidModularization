package com.example.modularandroidapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import com.example.modulandroidapp.ui_heroList.HeroList
import com.example.modulandroidapp.ui_heroList.ui.HeroListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imageLoader = ImageLoader.Builder(applicationContext)
            .availableMemoryPercentage(.25)
            .crossfade(true)
            .build()



        setContent {
            val heroListViewModel: HeroListViewModel = hiltViewModel()
            HeroList(state = heroListViewModel.state.value, imageLoader = imageLoader)
        }
    }
}