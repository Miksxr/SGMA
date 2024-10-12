package com.example.sgma.data.entity

import kotlinx.serialization.Serializable

// сущность которая отображается в полной версии страницы фильма/сериала/аниме
@Serializable
data class Multimedia(
    val id: Int,
    val nameRu: String,
    val image: String,
    val year: Int,
    val sgmaRating: Double,
    val kinopoiskReting: Double
)
