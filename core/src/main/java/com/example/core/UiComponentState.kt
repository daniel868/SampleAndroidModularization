package com.example.core

sealed class UiComponentState {
    object Show : UiComponentState()
    
    object Hide : UiComponentState()
}
