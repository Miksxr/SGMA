package com.example.sgma.domain.profile

data class Profile(
    val name: String,
    val image: Int,
    val password : String,
    val description: String,
    val comments : List<Comment>,
    val friends : List<String>,
    val statistic: Statistic,
    val login : String
)
