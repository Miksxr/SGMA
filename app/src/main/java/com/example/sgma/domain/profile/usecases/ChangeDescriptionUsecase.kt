package com.example.sgma.domain.profile.usecases

import com.example.sgma.domain.profile.ProfileRepository
import javax.inject.Inject

class ChangeDescriptionUsecase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(description : String, account: String) : Boolean {
        return repository.changeDescription(description, account)
    }
}