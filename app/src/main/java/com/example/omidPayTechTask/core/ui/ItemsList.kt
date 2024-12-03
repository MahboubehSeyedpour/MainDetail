package com.example.omidPayTechTask.core.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.omidPayTechTask.data.remote.model.ItemDTO

@Composable
fun ItemsList(items: List<ItemDTO>) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items.forEach {
            item {
                Item(it, onItemClicked = {})
                HorizontalDivider()
            }
        }
    }
}