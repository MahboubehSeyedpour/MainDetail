package com.example.omidPayTechTask.presentation.ui.detail.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.unit.sp

@Composable
fun DescriptionSection(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.titleMedium.copy(
            color = DarkGray, fontSize = 13.sp
        )
    )
}