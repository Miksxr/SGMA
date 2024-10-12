package com.example.sgma.data.entity

import kotlinx.serialization.Serializable

// на основе RAW
// сущность которая отображается в полной версии страницы игры
//@Serializable
data class Game(
    val id: Int,
    val name: String,
    val image: Int, // Изменил для фейков
    val year: Int,
    val sgmaRating: Double,
    val metacritic: Double,
    val description: String,
    val statusType: StatusType = StatusType.NONE // Я добавил
)
