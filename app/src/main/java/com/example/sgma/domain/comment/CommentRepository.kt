package com.example.sgma.domain.comment

interface CommentRepository {
    suspend fun getCommnets(filmId : Int) : List<Comment>

    suspend fun addComment(filmId : Int, comment: Comment) : Boolean
}