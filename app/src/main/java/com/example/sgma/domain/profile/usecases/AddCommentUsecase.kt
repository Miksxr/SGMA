package com.example.sgma.domain.profile.usecases

import com.example.sgma.domain.profile.Comment
import com.example.sgma.domain.profile.ProfileRepository
import javax.inject.Inject

class AddCommentUsecase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(comment : Comment, account: String) : Boolean {
        return repository.addComment(comment, account)
    }
}