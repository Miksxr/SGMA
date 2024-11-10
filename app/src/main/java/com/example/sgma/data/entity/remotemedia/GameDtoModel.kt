package com.example.sgma.data.entity.remotemedia

import com.example.sgma.data.entity.StatusType
import kotlinx.serialization.Serializable

@Serializable
data class GameDtoModel(
    val id: Int,
    val name: String,
    val image: Int, // Изменил для фейков
    val year: Int,
    val sgmaRating: Double,
    val metacritic: Double,
    val description: String,
    val statusType: StatusType = StatusType.None
)