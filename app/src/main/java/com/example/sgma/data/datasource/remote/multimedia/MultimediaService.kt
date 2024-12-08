package com.example.sgma.data.datasource.remote.multimedia

import com.example.sgma.data.entity.remotemedia.MediaDtoModel
import com.example.sgma.data.entity.remotemedia.MultimediaDtoModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MultimediaService {
    @GET("api/v2.2/films/{id}")
    fun getMultimedia(
        @Path("id") id : Int,
        @Header("X-API-KEY") key : String
    ) : Call<MultimediaDtoModel>

    @GET("api/v2.2/films/collections")
    fun getPopularMultimediaList(
        @Query("page") page : Int,
        @Header("X-API-KEY") key : String
    ) : Call<RemoteDatasourceMultimediaImpl.Handler>

    @GET("api/v2.2/films/{id}/images")
    fun getImages(
        @Path("id") id : Int,
        @Header("X-API-KEY") key : String,
        @Query("type") type : String = "STILL",
    ) : Call<RemoteDatasourceMultimediaImpl.ImageHandler>

    @GET("api/v2.1/films/search-by-keyword")
    fun findMediaByKeyword(
        @Query("keyword") keyword : String,
        @Header("X-API-KEY") key : String
    ) : Call<RemoteDatasourceMultimediaImpl.Handler>
}