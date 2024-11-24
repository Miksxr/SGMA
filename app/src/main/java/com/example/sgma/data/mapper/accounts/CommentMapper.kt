package com.example.sgma.data.mapper.accounts

import com.example.sgma.data.entity.account.CommentsDtoModel
import com.example.sgma.domain.comment.Comment

class CommentMapper {
    fun map(model : CommentsDtoModel) : Comment {
        return Comment(
            accountName = model.account_name,
            filmId = model.film_id,
            sgma_rating = model.sgma_rating,
            comment = model.comment
        )
    }

    fun mapToDtoModel(model : Comment) : CommentsDtoModel {
        return CommentsDtoModel(
            film_id = model.filmId,
            sgma_rating = model.sgma_rating,
            account_name = model.accountName,
            comment = model.comment
        )
    }
}
