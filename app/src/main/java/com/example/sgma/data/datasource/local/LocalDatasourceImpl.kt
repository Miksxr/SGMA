package com.example.sgma.data.datasource.local

import android.content.Context
import com.example.sgma.data.entity.MediaDBModel
import com.example.sgma.data.entity.StatusType
import javax.inject.Inject

// имплементация интерфейса LocalDatasource
class LocalDatasourceImpl @Inject constructor(
    private val context : Context
) : LocalDatasource {

    private val database : MediaDatabase
    init {
        database = MediaDatabase.newInstance(context)
    }

    override suspend fun insertMediaItem(mediaDBModel: MediaDBModel) {
        database.getMediaItemDao().insertMediaItem(mediaDBModel)
    }

    override suspend fun checkMediaInDatabase(id: Int) : MediaDBModel {
        return database.getMediaItemDao().checkMediaInDatabase(id)
    }

    override suspend fun getAllMedia(): List<MediaDBModel> {
        return database.getMediaItemDao().getAllMedia()
    }

    override suspend fun updateStatusType(type: String, id: Int) {
        database.getMediaItemDao().updateStatusType(type, id)
    }

    override suspend fun selectByType(statusType: String): List<MediaDBModel> {
        return database.getMediaItemDao().selectByType(statusType)
    }
}