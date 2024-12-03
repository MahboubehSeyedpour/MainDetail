package com.example.omidPayTechTask.presentation.navigation

import com.example.omidPayTechTask.R

val bottomNavItems = listOf(
    BottomNavigationItemModel(
        label = R.string.bn_home,
        icon = R.drawable.ic_home,
        route = Screens.Home.route
    ),
    BottomNavigationItemModel(
        label = R.string.bn_bookmark,
        icon = R.drawable.ic_bookmark,
        route = Screens.Bookmark.route
    )
)