package com.example.sgma.presentation.ui.fakelist

import com.example.sgma.R
import com.example.sgma.data.entity.ContentTypes
import com.example.sgma.data.entity.StatusType
import com.example.sgma.domain.media.Media

fun getFakeMediaList(): List<Media> {
    return listOf(
        Media(1, "Cyberpunk 2077", "", "10 Декабря, 2020", 8.5, 73.0, ContentTypes.Game, StatusType.Completed),
        Media(2, "Во все тяжкие", "", "2 Марта, 2017", 7.0, 65.0, ContentTypes.Serial, StatusType.Watched),
        Media(3, "Лимонные девочки", "", "21 Июня, 2006", 9.0, 85.0, ContentTypes.Anime, StatusType.Watching),
        Media(4, "Plants VS Zombies", "", "1 Января, 2000", 7.0, 65.0, ContentTypes.Game, StatusType.WatchedWalkthrough),
        Media(5, "Зелёный слоник", "", "14 Мая, 1988", 7.0, 65.0, ContentTypes.FILM, StatusType.HaventWatched),
    )
}
