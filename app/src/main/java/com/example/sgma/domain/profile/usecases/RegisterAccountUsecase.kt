package com.example.sgma.domain.profile.usecases

import com.example.sgma.domain.profile.Profile
import com.example.sgma.domain.profile.ProfileRepository
import javax.inject.Inject

class RegisterAccountUsecase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(account: Profile): Boolean {
        return repository.registerAccount(account)
    }
}