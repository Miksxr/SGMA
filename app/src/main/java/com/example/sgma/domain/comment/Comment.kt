package com.example.sgma.domain.comment

data class Comment(
    val username: String,
    val text: String,
    val date: String,
    val sgmaRating : Double,
    val avatar: Int
)