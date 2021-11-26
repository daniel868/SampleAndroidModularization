package com.example.core

sealed class UiComponent {

    data class Dialog(
        val title: String,
        val description: String
    ) : UiComponent()

    data class None(
        val title: String,
        val message: String
    ) : UiComponent()
}
