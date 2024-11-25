package com.example.sgma.domain.media.remote.multimedia

import com.example.sgma.data.entity.StatusType

// сущность которая отображается в полной версии страницы фильма/сериала/аниме
data class Multimedia(
    val id: Int,
    val nameRu: String,
    val image: String, // Изменил для фейков
    val year: Int,
    val sgmaRating: Double,
    val kinopoiskRating: Double,
    val description: String, // Я добавил
    val countries: String,
    val genres : String,
    val statusType: StatusType? = StatusType.None// Я добавил
) {
    constructor() : this(
        id = -1,
        nameRu = "",
        image = "",
        year = -1,
        sgmaRating = 0.0,
        kinopoiskRating = 0.0,
        description = "",
        countries = "",
        genres = ""
    )
}
