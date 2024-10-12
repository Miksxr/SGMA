package com.example.sgma.presentation.ui

import com.example.sgma.R
import com.example.sgma.data.entity.ContentTypes
import com.example.sgma.data.entity.Game
import com.example.sgma.data.entity.Media
import com.example.sgma.data.entity.Multimedia
import com.example.sgma.data.entity.StatusType

fun getFakeMediaList(): List<Media> {
    return listOf(
        Media(1, "Cyberpunk 2077", R.drawable.cyberpunk_2077, 2077, 8.5, 7.0, ContentTypes.GAME, StatusType.Completed),
        Media(2, "Во все тяжкие", R.drawable.breaking_bad, 2022, 7.0, 6.5, ContentTypes.SERIAL, StatusType.Watched),
        Media(3, "Лимонные девочки", R.drawable.kop, 2021, 9.0, 8.5, ContentTypes.ANIME, StatusType.Watching),
        Media(4, "Plants VS Zombies", R.drawable.pvzzz, 1092, 7.0, 6.5, ContentTypes.GAME, StatusType.WatchedWalkthrough),
        Media(5, "Зелёный слоник", R.drawable.nash_slon, 2007, 7.0, 6.5, ContentTypes.FILM, StatusType.HaventWatched),
    )
}
