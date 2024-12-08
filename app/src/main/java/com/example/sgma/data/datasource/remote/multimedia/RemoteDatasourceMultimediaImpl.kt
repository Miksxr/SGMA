package com.example.sgma.data.datasource.remote.multimedia

import android.util.Log
import com.example.sgma.data.datasource.remote.ApiClient
import com.example.sgma.data.entity.remotemedia.MediaDtoModel
import com.example.sgma.data.entity.remotemedia.MultimediaDtoModel
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

class RemoteDatasourceMultimediaImpl : RemoteDatasourceMultimedia {

    private val apiClient : ApiClient

    private val apiService : MultimediaService

    init {
        apiClient = ApiClient(
            url = "https://kinopoiskapiunofficial.tech/",
            secretKey = "SECRET_KEY"
        )
        apiService = apiClient.retrofit.create(MultimediaService::class.java)
    }

    override suspend fun getMultimedia(id: Int): MultimediaDtoModel {
        return MultimediaDtoModel(
            apiService.getMultimedia(id, apiClient.secretKey).await(),
            apiService.getImages(id, apiClient.secretKey).await().items.map { it.imageIrl }
        )
    }

    override suspend fun getPopularMultimediaList(page : Int): List<MediaDtoModel> {
        return apiService.getPopularMultimediaList(page, apiClient.secretKey).await().items
    }

    override suspend fun findMedia(keyword: String): List<MediaDtoModel> {
        return apiService.findMediaByKeyword(keyword, apiClient.secretKey).await().items
    }

    @Serializable
    data class Handler (
        @SerializedName("total") val total: Int,
        @SerializedName("totalPages") val totalPages: Int,
        @SerializedName(value = "items", alternate = ["films"]) val items : List<MediaDtoModel>
    ) {
        constructor() : this(-1, -1, emptyList())
    }

    @Serializable
    data class ImageHandler (
        @SerializedName("items") val items : List<Posters>
    ) {
        @Serializable
        data class Posters(
            @SerializedName("imageUrl") val imageIrl : String,
            @SerializedName("previewUrl") val previewUrl : String
        )
    }
}