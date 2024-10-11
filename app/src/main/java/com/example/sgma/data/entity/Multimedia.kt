package com.example.sgma.data.entity

import androidx.room.Entity
import kotlinx.serialization.Serializable

@Serializable
data class Multimedia(
    val id: Int,
    val nameRu: String,
    val image: String,
    val year: Int,
    val sgmaRating: Double,
    val kinopoiskReting: Double
)
