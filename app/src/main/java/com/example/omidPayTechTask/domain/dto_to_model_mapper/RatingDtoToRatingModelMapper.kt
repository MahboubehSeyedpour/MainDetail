package com.example.omidPayTechTask.domain.dto_to_model_mapper

import com.example.omidPayTechTask.data.remote.model.RatingDTO
import com.example.omidPayTechTask.domain.model.RatingModel

fun RatingDTO.toRatingModel(): RatingModel = RatingModel(
    rate = this.rate ?: 0f,
    count = this.count ?: 0
)