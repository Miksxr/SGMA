package com.example.sgma.domain.media.local.usecases

import com.example.sgma.domain.media.Media
import com.example.sgma.domain.media.local.LocalMediaRepository
import javax.inject.Inject

class InsertMediaUsecase @Inject constructor(
    private val repository: LocalMediaRepository
) {
    suspend operator fun invoke(media: Media) {
        return repository.insertMediaItem(media)
    }
}