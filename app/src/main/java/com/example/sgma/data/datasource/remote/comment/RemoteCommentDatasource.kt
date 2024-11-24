package com.example.sgma.data.datasource.remote.comment

import com.example.sgma.data.entity.account.CommentsDtoModel

interface RemoteCommentDatasource {
    suspend fun getCommnets(filmId : Int) : List<CommentsDtoModel>

    suspend fun addComment(filmId : Int, comment: CommentsDtoModel) : Boolean
}