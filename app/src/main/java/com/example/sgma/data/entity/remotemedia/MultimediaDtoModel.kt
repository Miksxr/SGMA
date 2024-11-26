package com.example.sgma.data.entity.remotemedia

import com.example.sgma.data.entity.StatusType
import com.example.sgma.domain.media.remote.multimedia.Multimedia
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MultimediaDtoModel(
    @SerializedName("kinopoiskId") val id: Int,
    @SerializedName("nameRu") val nameRu: String,
    @SerializedName("nameOriginal") val nameOriginal : String,
    @SerializedName("posterUrl") val image: String,
    @SerializedName("year") val year: Int,
    val sgmaRating: Double = 0.0,
    @SerializedName("ratingKinopoisk") val kinopoiskRating: Double,
    @SerializedName("description") val description: String,
    @SerializedName("countries") val countries : List<Country>,
    @SerializedName("genres") val genres : List<Genre>,
    @SerializedName("filmLength") val length : Int,
    @SerializedName("ratingAgeLimits") val ageLimits : String,
    val images : List<String>,
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

    constructor(md: MultimediaDtoModel, images : List<String>) : this(
        id = md.id,
        nameRu = md.nameRu,
        image = md.image,
        year = md.year,
        kinopoiskRating = md.kinopoiskRating,
        description = md.description,
        countries = md.countries,
        genres = md.genres,
        length = md.length,
        nameOriginal = md.nameOriginal ?: md.nameRu,
        ageLimits = md.ageLimits ?: "age0",
        images = images
    )
}


