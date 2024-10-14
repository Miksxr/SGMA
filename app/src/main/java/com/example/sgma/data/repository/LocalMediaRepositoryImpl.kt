package com.example.sgma.data.repository

import com.example.sgma.data.datasource.local.LocalDatasource
import com.example.sgma.data.entity.StatusType
import com.example.sgma.data.mapper.MediaDBModelMapper
import com.example.sgma.domain.Media
import com.example.sgma.domain.media.LocalMediaRepository
import javax.inject.Inject

class LocalMediaRepositoryImpl @Inject constructor(
    private val localDatasource: LocalDatasource,
    private val mapper: MediaDBModelMapper
) : LocalMediaRepository {
    override suspend fun insertMediaItem(mediaDBModel: Media) {
        localDatasource.insertMediaItem(mapper.mapTOMediaDbModel(mediaDBModel))
    }

    override suspend fun checkMediaInDatabase(id: Int): Media? {
        return mapper.map(localDatasource.checkMediaInDatabase(id))
    }

    override suspend fun getAllMedia(): List<Media> {
        return localDatasource.getAllMedia().map { mapper.map(it)!! }
    }

    override suspend fun updateStatusType(type: StatusType, id: Int) {
        localDatasource.updateStatusType(type.name, id)
    }

    override suspend fun selectByType(statusType: StatusType): List<Media?> {
        return localDatasource.selectByType(statusType.name).map { mapper.map(it) }
    }

    override suspend fun deleteMedia(media: Media) {
        localDatasource.deleteMedia(mapper.mapTOMediaDbModel(media))
    }
}