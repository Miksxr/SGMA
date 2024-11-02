package com.example.sgma.data.repository

import com.example.sgma.data.datasource.remote.comment.RemoteCommentDatasource
import com.example.sgma.data.mapper.accounts.CommentMapper
import com.example.sgma.domain.comment.CommentRepository
import com.example.sgma.domain.comment.Comment
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val remoteCommentDatasource: RemoteCommentDatasource,
    private val mapper : CommentMapper
) : CommentRepository {
    override suspend fun getCommnets(filmId: Int): List<Comment> {
        return remoteCommentDatasource.getCommnets(filmId).map { mapper.map(it) }
    }

    override suspend fun addComment(filmId: Int, comment: Comment): Boolean {
        return remoteCommentDatasource.addComment(filmId, mapper.mapToDtoModel(comment))
    }

}