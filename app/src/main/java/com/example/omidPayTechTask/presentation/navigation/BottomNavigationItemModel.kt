package com.example.omidPayTechTask.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.omidPayTechTask.R

data class BottomNavigationItemModel(
    @StringRes val label: Int,
    @DrawableRes val icon: Int = R.drawable.ic_launcher_foreground,
    val route: String = ""
)