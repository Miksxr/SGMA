package com.example.sgma.data.entity

import kotlinx.serialization.Serializable

// на основе RAW
// сущность которая отображается в полной версии страницы игры
@Serializable
data class Game(
    val id: Int,
    val name: String,
    val image: String,
    val year: Int,
    val sgmaRating: Double,
    val metacritic: Double,
    val description: String
)
