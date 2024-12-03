package com.example.omidPayTechTask.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.omidPayTechTask.ui.theme.Blue
import com.example.omidPayTechTask.ui.theme.Gray


@Composable
fun OmidPayBottomNavigation(
    navController: NavController,
    modifier: Modifier,
    bottomNavItems: List<BottomNavigationItemModel>
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    BottomAppBar(containerColor = White, modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        bottomNavItems.forEach { navigationItem ->

            val selected =
                currentDestination?.hierarchy?.any { it.route == navigationItem.route } == true

            NavigationBarItem(
                icon = {
                    Column(
                        modifier = Modifier
                            .width(screenWidth / (bottomNavItems.size))
                            .background(Color.Transparent) // Change background color on selection
                            .padding(8.dp)
                            .clip(RoundedCornerShape(5f))
                            .clickable {
                                navController.navigate(navigationItem.route) {
                                    // Pop up to the start destination of the graph to avoid building up a large stack of destinations on the back stack as users select items
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }

                                    currentDestination?.route?.let {
                                        popUpTo(it) {
                                            inclusive = true
                                        }
                                    }

                                    // Avoid multiple copies of the same destination when re-selecting the same item
                                    launchSingleTop = true
                                    // Restore state when re-selecting a previously selected item
                                    restoreState = true
                                }
                            },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Icon(
                            painter = painterResource(id = navigationItem.icon),
                            contentDescription = null,
                            tint = if (selected) Blue else Gray,
                            modifier = Modifier.size(25.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = stringResource(id = navigationItem.label),
                            maxLines = 1,
                            style = if (selected) {
                                MaterialTheme.typography.titleMedium.copy(
                                    color = Blue,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                )
                            } else {
                                MaterialTheme.typography.titleMedium.copy(
                                    color = DarkGray,
                                    fontSize = 10.sp
                                )
                            }
                        )
                    }
                },
                selected = selected,
                onClick = {},
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Green,
                    unselectedIconColor = Gray,
                    selectedTextColor = DarkGray,
                    unselectedTextColor = DarkGray,
                    indicatorColor = Color.Transparent // Remove default indicator color
                ),
                alwaysShowLabel = true, // Show label always
                interactionSource = remember { MutableInteractionSource() }.also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect { interaction ->
                            if (interaction is PressInteraction.Release) {
                                // Handle the press release event
                            }
                        }
                    }
                }
            )
        }
    }
}