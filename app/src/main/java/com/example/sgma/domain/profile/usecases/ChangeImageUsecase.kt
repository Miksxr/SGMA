package com.example.sgma.domain.profile.usecases

import com.example.sgma.domain.profile.ProfileRepository
import javax.inject.Inject

class ChangeImageUsecase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(id : Int, account: String) : Boolean {
        return repository.changeImage(id, account)
    }
}