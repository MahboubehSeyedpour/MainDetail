package com.example.omidPayTechTask.data.remote.model

data class ItemDTO(
    val id: Int,
    val title: String?,
    val price: Float?,
    val description: String?,
    val category: String?,
    val image: String?,
    val rating: RatingDTO?
)