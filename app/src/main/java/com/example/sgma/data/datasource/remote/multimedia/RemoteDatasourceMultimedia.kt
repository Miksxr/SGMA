package com.example.sgma.data.datasource.remote.multimedia

import com.example.sgma.data.entity.remotemedia.MediaDtoModel
import com.example.sgma.data.entity.remotemedia.MultimediaDtoModel

interface RemoteDatasourceMultimedia {
    suspend fun getMultimedia(id : Int) : MultimediaDtoModel

    suspend fun getPopularMultimediaList(page : Int = 1) : List<MediaDtoModel>
}