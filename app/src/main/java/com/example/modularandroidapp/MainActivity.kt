package com.example.modularandroidapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import com.example.detail_screen.ui.HeroDetail
import com.example.modulandroidapp.ui_heroList.ui.HeroList
import com.example.modulandroidapp.ui_heroList.ui.HeroListViewModel
import com.example.modularandroidapp.ui.navigation.Screen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var imageLoader: ImageLoader

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screen.HeroList.route,
                builder = {
                    //first destination -> HeroListScreen
                    addHeroList(navController, imageLoader)

                    //second destination -> HeroDetailScreen
                    addHeroDetail()
                }
            )
        }
    }
}

@ExperimentalComposeUiApi
fun NavGraphBuilder.addHeroList(
    navController: NavController,
    imageLoader: ImageLoader
) {
    composable(route = Screen.HeroList.route) {
        val heroListViewModel: HeroListViewModel = hiltViewModel()
        HeroList(
            state = heroListViewModel.state.value,
            events = {
                heroListViewModel.onTriggerEvent(it)
            },
            imageLoader = imageLoader,
            navigateToHeroDetailScreen = { heroId ->
                navController.navigate("${Screen.HeroDetail.route}/$heroId")
            }
        )
    }
}

fun NavGraphBuilder.addHeroDetail() {
    composable(
        route = Screen.HeroDetail.route + "/{heroId}",
        arguments = Screen.HeroDetail.arguments

    ) {
        HeroDetail(heroId = it.arguments?.get("heroId") as Int)
    }
}