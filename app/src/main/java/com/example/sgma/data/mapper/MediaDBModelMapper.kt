package com.example.sgma.data.mapper


import com.example.sgma.data.entity.ContentTypes
import com.example.sgma.data.entity.MediaDBModel
import com.example.sgma.data.entity.StatusType
import com.example.sgma.domain.Media

class MediaDBModelMapper {
    fun map(mediaDBModel: MediaDBModel) : Media {
        return Media (
            id = mediaDBModel.id,
            name = mediaDBModel.name,
            image = mediaDBModel.image,
            year = mediaDBModel.year,
            sgmaRating = mediaDBModel.sgmaRating,
            anotherRating = mediaDBModel.sgmaRating,
            type = ContentTypes.valueOf(mediaDBModel.type),
            statusType = StatusType.valueOf(mediaDBModel.statusType)
        )
    }

    fun mapTOMediaDbModel(media: Media) : MediaDBModel {
        return MediaDBModel(
            id = media.id,
            name = media.name,
            image = media.image,
            year = media.year,
            sgmaRating = media.sgmaRating,
            anotherRating = media.anotherRating,
            type = media.type.name,
            statusType = media.statusType.name
        )
    }
}