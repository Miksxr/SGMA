package com.example.sgma.data.entity.account

import com.example.sgma.R

data class AccountDtoModel(
    val name: String,
    val image: Int,
    val description: String,
    val password: String,
    val comments : List<CommentsDtoModel>,
    val friends : List<String>,
    val statistic: StatisticDtoModel,
    val login : String
) {
    constructor() : this(
        name = "",
        image = R.drawable.icon_profile,
        description = "",
        comments = emptyList(),
        friends = emptyList(),
        statistic = StatisticDtoModel(),
        password = "???",
        login = ""
    )
}
