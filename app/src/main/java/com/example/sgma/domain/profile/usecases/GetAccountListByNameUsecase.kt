package com.example.sgma.domain.profile.usecases

import com.example.sgma.domain.profile.Profile
import com.example.sgma.domain.profile.ProfileRepository
import javax.inject.Inject

class GetAccountListByNameUsecase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(account: String) : List<Profile> {
        return repository.getAccountListByName(account)
    }
}