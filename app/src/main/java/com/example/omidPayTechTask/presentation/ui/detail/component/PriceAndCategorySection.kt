package com.example.omidPayTechTask.presentation.ui.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun PriceAndCategorySection(price: Float, category: String) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = price.toString().plus("$"),
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color.Gray, fontSize = 15.sp, fontWeight = FontWeight.SemiBold
            )
        )

        Text(
            text = category,
            style = MaterialTheme.typography.titleMedium.copy(
                color = DarkGray, fontSize = 13.sp, fontWeight = FontWeight.SemiBold
            )
        )
    }
}