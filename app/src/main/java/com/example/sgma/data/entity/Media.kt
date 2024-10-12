package com.example.sgma.data.entity

import androidx.room.Entity

// сущность которая отображает элемент списка
@Entity("media")
data class Media(
    val id: Int,
    val name: String,
    val image: Int, // Изменил для фейков
    val year: Int,
    val sgmaRating: Double,
    val anotherRating: Double,
    val type: ContentTypes,
    val statusType: StatusType = StatusType.NONE
)
