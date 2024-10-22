package com.example.sgma.domain.profile

data class Profile(
    var name: String,
    var image: Int,
    var password : String,
    var description: String,
    var comments : List<Comment>,
    var friends : List<String>,
    val statistic: Statistic,
    val login : String
)
