package com.example.sgma.domain.profile.usecases

import com.example.sgma.domain.profile.ProfileRepository
import javax.inject.Inject

class AddFriendUsecase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(friendAcc : String, account: String) : Boolean {
        return repository.addFriend(friendAcc, account)
    }
}