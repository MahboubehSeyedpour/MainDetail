package com.example.omidPayTechTask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.omidPayTechTask.presentation.navigation.OmidPayBottomNavigation
import com.example.omidPayTechTask.presentation.navigation.Screens
import com.example.omidPayTechTask.presentation.navigation.bottomNavItems
import com.example.omidPayTechTask.presentation.ui.home.HomeScreen
import com.example.omidPayTechTask.ui.theme.OmidPayTechTaskTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(),
    NavController.OnDestinationChangedListener {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            //----------  init NavController -----------
            val navController = rememberNavController()
            navController.addOnDestinationChangedListener(this)


            OmidPayTechTaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    OmidPayApp(
                        modifier = Modifier.fillMaxSize(),
                        navController,
                        viewModel
                    )
                }
            }
        }
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        viewModel.isShowingBottomNavigation =
            bottomNavItems.map { it.route }.contains(destination.route)
    }

}

@Composable
fun OmidPayApp(modifier: Modifier, navController: NavHostController, viewModel: MainViewModel) {

    Scaffold(
        modifier = modifier,
        containerColor = White,
        bottomBar = {
            if (viewModel.isShowingBottomNavigation) {
                OmidPayBottomNavigation(
                    navController = navController,
                    modifier = Modifier.fillMaxWidth(),
                    bottomNavItems = bottomNavItems
                )
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screens.Home.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
//            composable(Screens.Start.route) { StartScreen(navController) }
            composable(Screens.Home.route) { HomeScreen(navController) }
//            composable(Screens.Details.route) { LoginScreen(navController) }
//            composable(Screens.Bookmark.route) { ProfileScreen(navController) }
        }
    }
}