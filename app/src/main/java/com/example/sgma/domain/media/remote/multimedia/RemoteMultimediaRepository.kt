package com.example.sgma.domain.media.remote.multimedia

import com.example.sgma.domain.media.Media

interface RemoteMultimediaRepository {
    suspend fun getMultimedia(id : Int) : Multimedia

    suspend fun getPopularMultimediaList(page : Int = 1) : List<Media>

    suspend fun findMedia(keyword : String) : List<Media>
}