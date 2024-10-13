package com.example.sgma.data.datasource.local

import com.example.sgma.data.entity.MediaDBModel
import com.example.sgma.data.entity.StatusType

// интерфейс который описывает все функции работы с локальным ресурсом данных
interface LocalDatasource {

    suspend fun insertMediaItem(mediaDBModel: MediaDBModel)

    suspend fun checkMediaInDatabase(id: Int) : MediaDBModel

    suspend fun getAllMedia() : List<MediaDBModel>

    suspend fun updateStatusType(type: String, id: Int)

    suspend fun selectByType(statusType: String) : List<MediaDBModel>

}