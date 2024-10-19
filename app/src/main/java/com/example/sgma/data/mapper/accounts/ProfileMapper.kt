package com.example.sgma.data.mapper.accounts

import com.example.sgma.data.entity.account.AccountDtoModel
import com.example.sgma.domain.profile.Profile

class ProfileMapper {
    val commentMapper : CommentMapper = CommentMapper()
    val statisticMapper : StatisticMapper = StatisticMapper()


    fun map(accountDtoModel: AccountDtoModel) : Profile {
        return Profile(
            name = accountDtoModel.name,
            image = accountDtoModel.Image,
            description = accountDtoModel.description,
            comments = accountDtoModel.comments.map { commentMapper.map(it) },
            friends = accountDtoModel.friends,
            statistic = statisticMapper.map(accountDtoModel.statistic),
            login = accountDtoModel.login
        )
    }

    fun mapToAccountDtoModel(profile: Profile) : AccountDtoModel {
        return AccountDtoModel(
            name = profile.name,
            Image = profile.image,
            description = profile.description,
            comments = profile.comments.map { commentMapper.mapToDtoModel(it) },
            friends = profile.friends,
            statistic = statisticMapper.mapToDtoModel(profile.statistic),
            password = "???",
            login = profile.login
        )
    }
}