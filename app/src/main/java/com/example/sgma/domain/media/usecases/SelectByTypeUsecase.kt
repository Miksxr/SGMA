package com.example.sgma.domain.media.usecases

import com.example.sgma.data.entity.StatusType
import com.example.sgma.domain.Media
import com.example.sgma.domain.media.LocalMediaRepository
import javax.inject.Inject

class SelectByTypeUsecase @Inject constructor(
    private val repository: LocalMediaRepository
) {
    suspend operator fun invoke(statusType: StatusType) : List<Media>{
        return repository.selectByType(statusType)
    }
}