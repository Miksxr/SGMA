package com.example.sgma.domain.media.usecases

import com.example.sgma.domain.Media
import com.example.sgma.domain.media.LocalMediaRepository
import javax.inject.Inject

class CheckMediaInDBUsecase @Inject constructor(
    private val repository: LocalMediaRepository
) {
    suspend operator fun invoke(id: Int) : Media {
        return repository.checkMediaInDatabase(id)
    }
}