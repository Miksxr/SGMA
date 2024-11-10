package com.example.sgma.data.repository

import com.example.sgma.data.datasource.remote.multimedia.RemoteDatasourceMultimedia
import com.example.sgma.data.mapper.MediaDtoModelMapper
import com.example.sgma.data.mapper.multimedia.MultimediaDtoModelMapper
import com.example.sgma.domain.media.Media
import com.example.sgma.domain.media.remote.multimedia.Multimedia
import com.example.sgma.domain.media.remote.multimedia.RemoteMultimediaRepository

class RemoteMultimediaRepositoryImpl(
    private val remoteDatasourceMultimedia: RemoteDatasourceMultimedia,
    private val mediaDtoModelMapper: MediaDtoModelMapper,
    private val multimedaMapper: MultimediaDtoModelMapper
) : RemoteMultimediaRepository {
    override suspend fun getMultimedia(id: Int): Multimedia {
        return multimedaMapper.map(remoteDatasourceMultimedia.getMultimedia(id))
    }

    override suspend fun getPopularMultimediaList(page: Int): List<Media> {
        return remoteDatasourceMultimedia.getPopularMultimediaList(page).map { mediaDtoModelMapper.map(it) }
    }
}