package com.example.sgma.domain

import com.example.sgma.data.entity.ContentTypes
import com.example.sgma.data.entity.StatusType

// сущность которая отображает элемент списка
data class Media(
    val id: Int,
    val name: String,
    val image: Int, // Изменил для фейков
    val year: Int,
    val sgmaRating: Double,
    val anotherRating: Double,
    val type: ContentTypes,
    val statusType: StatusType = StatusType.None
)
