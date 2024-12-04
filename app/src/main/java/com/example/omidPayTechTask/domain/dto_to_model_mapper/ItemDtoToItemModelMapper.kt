package com.example.omidPayTechTask.domain.dto_to_model_mapper

import com.example.omidPayTechTask.data.remote.model.ItemDTO
import com.example.omidPayTechTask.domain.model.ItemModel
import com.example.omidPayTechTask.domain.model.RatingModel

fun ItemDTO.toItemModel(): ItemModel = ItemModel(
    id = this.id,
    title = this.title ?: "",
    price = this.price ?: 0f,
    image = this.image ?: "",
    rating = RatingModel(rate = this.rating?.rate ?: 5f, count = this.rating?.count ?: 0),
    category = this.category ?: "",
    description = this.description ?: ""
)