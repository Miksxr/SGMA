package com.example.sgma.data.entity.account

data class StatisticDtoModel(
    val anime : Int,
    val films : Int,
    val serials : Int,
    val games : Int,
) {
    constructor() : this(
        -1,
        -1,
        -1,
        -1,
    )
}