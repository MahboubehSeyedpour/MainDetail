package com.example.omidPayTechTask.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.omidPayTechTask.R
import com.example.omidPayTechTask.domain.model.ItemModel
import com.example.omidPayTechTask.ui.theme.LightGray


@Composable
fun Item(item: ItemModel, onItemClicked: (Int) -> Unit) {

    Row(
        modifier = Modifier
            .padding(12.dp)
            .clickable { onItemClicked(item.id) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        ImageAndPriceSection(item)
        ItemDetailSection(item)

    }
}

@Composable
fun ImageAndPriceSection(item: ItemModel) {
    Column {

        AsyncImage(
            model = item.image,
            contentDescription = "image",
            modifier = Modifier.size(70.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(LightGray)
                .padding(vertical = 3.dp, horizontal = 8.dp)
        ) {
            Text(
                text = item.price.toString().plus("$"),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.Gray, fontSize = 15.sp, fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}


@Composable
fun ItemDetailSection(item: ItemModel) {
    Column(modifier = Modifier.padding(12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.Gray, fontSize = 15.sp, fontWeight = FontWeight.SemiBold
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = "star",
                    modifier = Modifier.size(18.dp),
                    tint = DarkGray
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = item.rating.let { rating ->
                        val rateText = rating.rate?.toString() ?: "-"
                        val countText = rating.count?.let { "($it)" } ?: ""
                        rateText + countText
                    },
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = DarkGray, fontSize = 13.sp
                    ))
            }

            Text(
                text = item.category,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = DarkGray, fontSize = 13.sp, fontWeight = FontWeight.SemiBold
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = item.description,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = DarkGray, fontSize = 13.sp
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.height(3.dp))
    }
}