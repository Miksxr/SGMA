package com.example.sgma.data.entity

import kotlinx.serialization.Serializable

// сущность которая отображается в полной версии страницы фильма/сериала/аниме
//@Serializable
data class Multimedia(
    val id: Int,
    val nameRu: String,
    val image: Int, // Изменил для фейков
    val year: Int,
    val sgmaRating: Double,
    val kinopoiskReting: Double,
    val description: String, // Я добавил
    val statusType: StatusType = StatusType.NONE // Я добавил
)
