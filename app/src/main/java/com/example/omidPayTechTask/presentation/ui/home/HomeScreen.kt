package com.example.omidPayTechTask.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.omidPayTechTask.core.ui.ItemsList
import com.example.omidPayTechTask.presentation.ui.home.components.CustomSearchBar


@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    val items = viewModel.items.collectAsStateWithLifecycle().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
    ) {

        CustomSearchBar(
            modifier = Modifier.fillMaxWidth(),
            onSearchClicked = {},
            query = viewModel.searchbarQuery,
            onQueryChange = { value -> viewModel.onSearchQueryChanged(value) },
            onDeleteClicked = { viewModel.onSearchQueryChanged("") }
        )

        ItemsList(items = items)
    }
}