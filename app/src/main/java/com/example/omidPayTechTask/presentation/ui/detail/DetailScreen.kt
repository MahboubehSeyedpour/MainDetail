package com.example.omidPayTechTask.presentation.ui.detail

import ImageAndStarSection
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.omidPayTechTask.presentation.animation.BallRotateIndicator
import com.example.omidPayTechTask.presentation.ui.detail.component.DescriptionSection
import com.example.omidPayTechTask.presentation.ui.detail.component.PriceAndCategorySection
import com.example.omidPayTechTask.presentation.ui.detail.component.TitleSection
import com.example.omidPayTechTask.ui.theme.Blue

@Composable
fun DetailScreen(navController: NavController, viewModel: DetailViewModel = hiltViewModel()) {

    val item = viewModel.item.collectAsStateWithLifecycle().value

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 24.dp, horizontal = 32.dp)) {

        item?.let { item ->

            ImageAndStarSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                item.image,
                item.rating.rate,
                item.rating.count
            )

            Spacer(modifier = Modifier.height(16.dp))

            PriceAndCategorySection(item.price, item.category)

            Spacer(modifier = Modifier.height(16.dp))

            TitleSection(item.title)

            Spacer(modifier = Modifier.height(16.dp))

            DescriptionSection(item.description)

            Spacer(modifier = Modifier.height(16.dp))

        } ?: BallRotateIndicator(modifier = Modifier.fillMaxSize(), color = Blue)
    }
}