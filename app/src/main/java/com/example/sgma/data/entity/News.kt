package com.example.sgma.data.entity

data class News(
    val id: Int,
    val name: String,
    val image: Int,
    val media: String,
    val statusType: StatusType = StatusType.None
)

