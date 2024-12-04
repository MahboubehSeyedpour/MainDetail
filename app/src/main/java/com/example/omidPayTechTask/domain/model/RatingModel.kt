package com.example.omidPayTechTask.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RatingModel(
    val rate: Float,
    val count: Int
) : Parcelable