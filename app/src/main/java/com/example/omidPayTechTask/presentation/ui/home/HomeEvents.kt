package com.example.omidPayTechTask.presentation.ui.home

sealed class HomeEvents {
    class NavigateToDetailScreen(val item: String) : HomeEvents()
}