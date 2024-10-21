package com.example.sgma.domain.profile.usecases

import com.example.sgma.domain.profile.ProfileRepository
import javax.inject.Inject

class ChangeNameUsecase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(name : String, account: String) : Boolean {
        return repository.changeName(name, account)
    }
}