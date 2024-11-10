package com.example.sgma.domain.media.local.usecases

import com.example.sgma.data.entity.StatusType
import com.example.sgma.domain.media.local.LocalMediaRepository
import javax.inject.Inject

class UpdateStatusTypeUsecase @Inject constructor(
    private val repository: LocalMediaRepository
) {
    suspend operator fun invoke(statusType: StatusType, id: Int) {
        repository.updateStatusType(statusType, id)
    }
}
