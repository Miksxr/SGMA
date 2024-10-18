package com.example.sgma.data.entity.account

data class CommentsDtoModel(
    val filmId: Int,
    val sgma_rating: Double,
    val accountName: String,
    val comment: String
) {
    constructor() : this(
        -1,
        -1.0,
        "",
        ""
    )
}