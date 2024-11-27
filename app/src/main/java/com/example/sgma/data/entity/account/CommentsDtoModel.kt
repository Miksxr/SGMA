package com.example.sgma.data.entity.account

import com.example.sgma.R

data class CommentsDtoModel(
    val username: String,
    val text: String,
    val date: String,
    val sgmaRating : Double,
    val avatar: Int
) {
    constructor() : this(
        "",
        "",
        "",
        0.0,
        R.drawable.no_user
    )
}