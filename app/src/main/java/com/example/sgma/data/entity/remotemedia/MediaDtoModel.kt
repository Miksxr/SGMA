package com.example.sgma.data.entity.remotemedia

import com.example.sgma.data.entity.ContentTypes
import com.example.sgma.data.entity.StatusType
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class MediaDtoModel(
    @SerializedName(value = "kinopoiskId", alternate = []) val id: Int,
    @SerializedName(value = "nameRu", alternate = []) val name: String,
    @SerializedName(value = "posterUrl", alternate = []) val image: String,
    @SerializedName(value = "year", alternate = []) val year: Int,
    val sgmaRating: Double = 0.0,
    @SerializedName(value = "ratingKinopoisk", alternate = []) val anotherRating: Double,
    @SerializedName(value = "type", alternate = []) val type: String,
    val statusType: StatusType = StatusType.None
)