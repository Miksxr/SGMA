package com.example.sgma.data.mapper.accounts

import com.example.sgma.data.entity.account.AccountDtoModel
import com.example.sgma.domain.profile.Profile

class ProfileMapper {
    val commentMapper : CommentMapper = CommentMapper()
    val statisticMapper : StatisticMapper = StatisticMapper()


    fun map(accountDtoModel: AccountDtoModel) : Profile {
        return Profile(
            name = accountDtoModel.name,
            image = accountDtoModel.image,
            description = accountDtoModel.description,
            comments = accountDtoModel.comments.map { commentMapper.map(it) },
            friends = accountDtoModel.friends,
            statistic = statisticMapper.map(accountDtoModel.statistic)
        )
    }

    fun mapToAccountDtoModel(profile: Profile) : AccountDtoModel {
        return AccountDtoModel(
            name = profile.name,
            image = profile.image,
            description = profile.description,
            comments = profile.comments.map { commentMapper.mapToComments(it) },
            friends = profile.friends,
            statistic = statisticMapper.mapToDtoModel(profile.statistic),
            password = "???"
        )
    }
}