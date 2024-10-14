package com.example.sgma.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// модель для хранения в бд
@Entity("media")
data class MediaDBModel(
    @PrimaryKey val id: Int,
    val name: String,
    val image: Int, // Изменил для фейков
    val year: Int,
    val sgmaRating: Double,
    val anotherRating: Double,
    val type: String,
    val statusType: String = StatusType.None.name
)
