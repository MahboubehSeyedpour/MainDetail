package com.example.omidPayTechTask.presentation.navigation

sealed class Screens(val route: String) {
    data object Start : Screens("start")
    data object Home : Screens("home")
    data object Details : Screens("details")
    data object Bookmark : Screens("bookmark")
}