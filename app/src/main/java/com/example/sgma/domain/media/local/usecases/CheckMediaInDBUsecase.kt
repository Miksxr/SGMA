package com.example.sgma.domain.media.local.usecases

import com.example.sgma.domain.media.Media
import com.example.sgma.domain.media.local.LocalMediaRepository
import javax.inject.Inject

class CheckMediaInDBUsecase @Inject constructor(
    private val repository: LocalMediaRepository
) {
    suspend operator fun invoke(id: Int) : Media? {
        return repository.checkMediaInDatabase(id)
    }
}