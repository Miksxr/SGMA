package com.example.sgma.data.entity.remotemedia

import com.example.sgma.data.entity.StatusType
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MultimediaDtoModel(
    @SerializedName("kinopoiskId") val id: Int,
    @SerializedName("nameRu") val nameRu: String,
    @SerializedName("posterUrl") val image: String, // Изменил для фейков
    @SerializedName("year") val year: Int,
    val sgmaRating: Double = 0.0,
    @SerializedName("ratingKinopoisk") val kinopoiskRating: Double,
    @SerializedName("description") val description: String, // Я добавил
    @SerializedName("countries") val countries : List<Country>,
    @SerializedName("genres") val genres : List<Genre>,
    val statusType: StatusType = StatusType.None
) {
    @Serializable
    data class Country (
        @SerializedName("country") val country : String
    )

    @Serializable
    data class Genre (
        @SerializedName("genre") val genre: String
    )
}


