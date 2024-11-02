package com.example.sgma.data.entity.account

data class CommentsDtoModel(
    val film_id: Int,
    val sgma_rating: Double,
    val account_name: String,
    val comment: String
) {
    constructor() : this(
        -1,
        -1.0,
        "",
        ""
    )
}