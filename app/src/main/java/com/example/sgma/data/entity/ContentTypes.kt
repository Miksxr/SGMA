package com.example.sgma.data.entity

import android.content.Context
import com.example.sgma.R

// типы возможного контента в приложении служит для корректного мапинга к правильной сущности
enum class ContentTypes(private val resId: Int) {
    FILM(R.string.type_film),
    Game(R.string.type_game),
    Anime(R.string.type_anime),
    Serial(R.string.type_tv_series),
    Unknown(R.string.type_unknown),
    VIDEO(R.string.type_video),
    TV_SERIES(R.string.type_tv_series),
    MINI_SERIES(R.string.type_mini_series),
    TV_SHOW(R.string.type_tv_show);

    fun getLocalizedString(context: Context): String = context.getString(resId)
}