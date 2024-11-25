package com.example.sgma.data.mapper.accounts

import com.example.sgma.data.entity.account.CommentsDtoModel
import com.example.sgma.domain.comment.Comment

class CommentMapper {
    fun map(model : CommentsDtoModel) : Comment {
        return Comment(
            username = model.username,
            text = model.text,
            date = model.date,
            avatar = model.avatar
        )
    }

    fun mapToDtoModel(model : Comment) : CommentsDtoModel {
        return CommentsDtoModel(
            username = model.username,
            text = model.text,
            date = model.date,
            avatar = model.avatar
        )
    }
}
