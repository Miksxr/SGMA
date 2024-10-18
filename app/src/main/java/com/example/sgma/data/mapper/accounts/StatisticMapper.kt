package com.example.sgma.data.mapper.accounts

import com.example.sgma.data.entity.account.StatisticDtoModel
import com.example.sgma.domain.profile.Statistic

class StatisticMapper {
    fun map(model : StatisticDtoModel) : Statistic {
        return Statistic(
            anime = model.anime,
            films = model.films,
            serials = model.serials,
            games = model.games
        )
    }

    fun mapToDtoModel(model : Statistic) : StatisticDtoModel {
        return StatisticDtoModel(
            anime = model.anime,
            films = model.films,
            serials = model.serials,
            games = model.games
        )
    }
}