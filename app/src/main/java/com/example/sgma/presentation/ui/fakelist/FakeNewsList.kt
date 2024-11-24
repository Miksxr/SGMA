package com.example.sgma.presentation.ui.fakelist

import com.example.sgma.R
import com.example.sgma.data.entity.News
import com.example.sgma.data.entity.StatusType

fun getFakeNewsList(): List<News> {
    return listOf(
        News(1, "Артём", R.drawable.artem, "Зомби Ферма", "12.12.2024", StatusType.InPlans),
        News(2, "Игорь", R.drawable.igor, "Plants VS Zombie","11.12.2024", StatusType.WatchedWalkthrough),
        News(3, "Дядя Богдан", R.drawable.dyadya_bogdan, "Сумерки","10.12.2024", StatusType.Rewatching)
    )
}