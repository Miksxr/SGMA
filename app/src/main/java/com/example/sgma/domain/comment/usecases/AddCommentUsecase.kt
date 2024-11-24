package com.example.sgma.domain.comment.usecases

import com.example.sgma.domain.comment.CommentRepository
import com.example.sgma.domain.comment.Comment
import javax.inject.Inject

class AddCommentUsecase @Inject constructor(
    private val repository: CommentRepository
) {
    suspend operator fun invoke(filmId : Int, comment: Comment) : Boolean {
        return repository.addComment(filmId, comment)
    }
}