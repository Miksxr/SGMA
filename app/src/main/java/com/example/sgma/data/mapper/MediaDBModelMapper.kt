package com.example.sgma.data.mapper


import com.example.sgma.data.entity.ContentTypes
import com.example.sgma.data.entity.localmedia.MediaDBModel
import com.example.sgma.data.entity.StatusType
import com.example.sgma.domain.media.Media

class MediaDBModelMapper {
    fun map(mediaDBModel: MediaDBModel?) : Media {
        return Media (
            id = mediaDBModel?.id ?: -1,
            name = mediaDBModel?.name ?: "",
            image = mediaDBModel?.image ?: -1,
            year = mediaDBModel?.year ?: "",
            sgmaRating = mediaDBModel?.sgmaRating ?: -1.0,
            anotherRating = mediaDBModel?.anotherRating ?: -1,
            type = ContentTypes.valueOf(mediaDBModel?.type ?: ContentTypes.Unknown.name),
            description = mediaDBModel?.description ?: "",
            statusType = StatusType.valueOf(mediaDBModel?.statusType ?: StatusType.None.name)
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
            description = media.description,
            statusType = media.statusType.name
        )
    }
}