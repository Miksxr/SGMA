package com.example.sgma.domain.profile

import com.example.sgma.data.entity.account.StatisticDtoModel

data class Profile(
    val name: String,
    val image: Int,
    val description: String,
    val comments : List<Comment>,
    val friends : List<String>,
    val statistic: Statistic,
    val login : String
)
