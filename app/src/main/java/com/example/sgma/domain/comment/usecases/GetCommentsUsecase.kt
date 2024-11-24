package com.example.sgma.domain.comment.usecases

import com.example.sgma.domain.comment.CommentRepository
import com.example.sgma.domain.comment.Comment
import javax.inject.Inject

class GetCommentsUsecase @Inject constructor(
    private val commentRepository: CommentRepository
) {
    suspend operator fun invoke(filmId : Int) : List<Comment> {
        return commentRepository.getCommnets(filmId)
    }
}