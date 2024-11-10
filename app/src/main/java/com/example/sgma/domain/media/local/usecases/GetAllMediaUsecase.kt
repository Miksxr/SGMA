package com.example.sgma.domain.media.local.usecases

import com.example.sgma.domain.media.Media
import com.example.sgma.domain.media.local.LocalMediaRepository
import javax.inject.Inject

class GetAllMediaUsecase @Inject constructor(
    private val repository: LocalMediaRepository
) {
    suspend operator fun invoke() : List<Media> {
        return repository.getAllMedia()
    }
}