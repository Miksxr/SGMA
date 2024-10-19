package com.example.sgma.data.mapper.accounts

import com.example.sgma.data.entity.account.CommentsDtoModel
import com.example.sgma.domain.profile.Comment

class CommentMapper {
    fun map(model : CommentsDtoModel) : Comment {
        return Comment(
            accountName = model.accountName,
            filmId = model.filmId,
            sgma_rating = model.sgma_rating,
            comment = model.comment
        )
    }

    fun mapToDtoModel(model : Comment) : CommentsDtoModel {
        return CommentsDtoModel(
            filmId = model.filmId,
            sgma_rating = model.sgma_rating,
            accountName = model.accountName,
            comment = model.comment
        )
    }
}
