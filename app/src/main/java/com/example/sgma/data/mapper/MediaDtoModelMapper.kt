package com.example.sgma.data.mapper

import com.example.sgma.R
import com.example.sgma.data.entity.ContentTypes
import com.example.sgma.data.entity.StatusType
import com.example.sgma.data.entity.remotemedia.MediaDtoModel
import com.example.sgma.domain.media.Media

class MediaDtoModelMapper {
    fun map(mediaDtoModel : MediaDtoModel) : Media {
        return Media(
            id = mediaDtoModel.id ?: -1,
            name = mediaDtoModel.name ?: "",
            image = mediaDtoModel.image,
            year = mediaDtoModel.year.toString(),
            sgmaRating = mediaDtoModel.sgmaRating ?: -0.0,
            anotherRating = mediaDtoModel.anotherRating ?: -0.0,
            type = ContentTypes.valueOf(mediaDtoModel.type) ?: ContentTypes.Unknown,
            statusType = mediaDtoModel.statusType ?: StatusType.None
        )
    }
}