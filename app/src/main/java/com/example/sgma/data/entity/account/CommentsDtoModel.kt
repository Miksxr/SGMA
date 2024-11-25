package com.example.sgma.data.entity.account

import com.example.sgma.R

data class CommentsDtoModel(
    val username: String,
    val text: String,
    val date: String,
    val avatar: Int
) {
    constructor() : this(
        "",
        "",
        "",
        R.drawable.no_user
    )
}