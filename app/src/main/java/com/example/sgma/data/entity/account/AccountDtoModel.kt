package com.example.sgma.data.entity.account

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
        image = 12,
        description = "",
        comments = emptyList(),
        friends = emptyList(),
        statistic = StatisticDtoModel(
            12,12,12,12
        ),
        password = "???",
        login = ""
    )
}
