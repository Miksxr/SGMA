package com.example.sgma.data.mapper.multimedia

import com.example.sgma.R
import com.example.sgma.data.entity.remotemedia.MultimediaDtoModel
import com.example.sgma.domain.media.remote.multimedia.Multimedia

class MultimediaDtoModelMapper {
    fun map(multimediaDtoModel: MultimediaDtoModel) : Multimedia {
        return Multimedia(
            id = multimediaDtoModel.id,
            nameRu = multimediaDtoModel.nameRu,
            image = multimediaDtoModel.image,
            year = multimediaDtoModel.year,
            sgmaRating = multimediaDtoModel.sgmaRating,
            kinopoiskRating = multimediaDtoModel.kinopoiskRating,
            description = multimediaDtoModel.description ?: "Описания пока нету",
            countries = multimediaDtoModel.countries.joinToString(", ") { it.country },
            genres = multimediaDtoModel.genres.joinToString(", ") { it.genre },
            length = multimediaDtoModel.length.toString(),
            images = multimediaDtoModel.images,
            nameOriginal = multimediaDtoModel.nameOriginal,
            ageLimit = multimediaDtoModel.ageLimits,
            statusType = multimediaDtoModel.statusType
        )
    }
}