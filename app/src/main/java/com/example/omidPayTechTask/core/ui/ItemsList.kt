package com.example.omidPayTechTask.core.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.omidPayTechTask.domain.model.ItemModel

@Composable
fun ItemsList(items: List<ItemModel>, onItemClicked: (Int) -> Unit) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items.forEach {
            item {
                Item(it, onItemClicked = onItemClicked)
                HorizontalDivider()
            }
        }
    }
}