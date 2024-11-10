package com.example.sgma.domain.media

import com.example.sgma.R
import com.example.sgma.data.entity.ContentTypes
import com.example.sgma.data.entity.StatusType

// сущность которая отображает элемент списка
data class Media(
    val id: Int,
    val name: String,
    val image: String, // Изменил для фейков
    val year: Int,
    val sgmaRating: Double,
    val anotherRating: Double,
    val type: ContentTypes,
    val statusType: StatusType? = StatusType.None
)

