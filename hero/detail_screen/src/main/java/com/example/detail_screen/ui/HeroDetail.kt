package com.example.detail_screen.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun HeroDetail(
    heroId: Int?
){
    Text(text = "Clicked hero id : $heroId")
}