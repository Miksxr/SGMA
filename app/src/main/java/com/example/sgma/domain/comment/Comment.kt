package com.example.sgma.domain.comment

data class Comment(
    val filmId: Int,
    val sgma_rating: Double,
    val accountName: String,
    val comment: String
)
