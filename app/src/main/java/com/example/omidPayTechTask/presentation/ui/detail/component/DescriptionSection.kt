package com.example.omidPayTechTask.presentation.ui.detail.component

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.unit.sp

@Composable
fun DescriptionSection(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.titleMedium.copy(
            color = DarkGray, fontSize = 18.sp
        ),
        modifier = Modifier.verticalScroll(rememberScrollState())
    )
}