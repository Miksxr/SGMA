package com.example.sgma.domain.media.usecases

import com.example.sgma.domain.media.Media
import com.example.sgma.domain.media.LocalMediaRepository
import javax.inject.Inject

class DeleteMediaUsecase @Inject constructor(
    private val repository: LocalMediaRepository
) {
    suspend operator fun invoke(media: Media) {
        return repository.deleteMedia(media)
    }
}