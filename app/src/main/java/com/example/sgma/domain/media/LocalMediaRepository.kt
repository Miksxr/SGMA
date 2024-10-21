package com.example.sgma.domain.media

import com.example.sgma.data.entity.StatusType

// интерфейс с которым будут работать usecase
interface LocalMediaRepository {
    suspend fun insertMediaItem(mediaDBModel: Media)

    suspend fun checkMediaInDatabase(id: Int) : Media?

    suspend fun getAllMedia() : List<Media>

    suspend fun updateStatusType(type: StatusType, id: Int)

    suspend fun selectByType(statusType: StatusType) : List<Media?>

    suspend fun deleteMedia(media: Media)
}