package com.example.sgma.data.entity

import androidx.room.Entity

// сущность которая отображает элемент списка
@Entity("media")
data class Media(
    val id: Int,
    val name: String,
    val image: String,
    val year: Int,
    val sgmaRating: Double,
    val anotherRating: Double,
    val type: ContentTypes,
    val statusType: StatusType = StatusType.NONE
)
