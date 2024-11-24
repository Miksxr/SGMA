package com.example.sgma.data.entity

// сущность которая отображается в полной версии страницы фильма/сериала/аниме
//@Serializable
data class Multimedia(
    val id: Int,
    val nameRu: String,
    val image: Int, // Изменил для фейков
    val year: String,
    val sgmaRating: Double,
    val kinopoiskReting: Int,
    val description: String, // Я добавил
    val statusType: StatusType = StatusType.None // Я добавил
)
