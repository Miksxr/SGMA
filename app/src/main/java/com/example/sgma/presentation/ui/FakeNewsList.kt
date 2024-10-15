package com.example.sgma.presentation.ui

import com.example.sgma.R
import com.example.sgma.data.entity.ContentTypes
import com.example.sgma.data.entity.News
import com.example.sgma.data.entity.StatusType
import com.example.sgma.domain.Media

fun getFakeNewsList(): List<News> {
    return listOf(
        News(1, "Артём", R.drawable.artem, "Зомби Ферма", StatusType.InPlans),
        News(1, "Игорь", R.drawable.igor, "Plants VS Zombie", StatusType.WatchedWalkthrough),
        News(1, "Дядя Богдан", R.drawable.dyadya_bogdan, "Сумерки", StatusType.Rewatching)
    )
}