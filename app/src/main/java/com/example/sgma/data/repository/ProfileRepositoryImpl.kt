package com.example.sgma.data.repository

import com.example.sgma.data.datasource.remote.accounts.RemoteAccountDatasource
import com.example.sgma.data.mapper.accounts.ProfileMapper
import com.example.sgma.domain.profile.Comment
import com.example.sgma.domain.profile.Profile
import com.example.sgma.domain.profile.ProfileRepository

class ProfileRepositoryImpl(
    private val remoteAccountDatasource: RemoteAccountDatasource,
    private val mapper: ProfileMapper
) : ProfileRepository{
    override suspend fun changeImage(id: Int, accountName: String) : Boolean {
        return remoteAccountDatasource.changeImage(id, accountName)
    }

    override suspend fun changeName(name: String, accountName: String) : Boolean {
        return remoteAccountDatasource.changeName(name, accountName)
    }

    override suspend fun changeDescription(description: String, accountName: String) : Boolean {
        return remoteAccountDatasource.changeDescription(description, accountName)
    }

    override suspend fun addFriend(friendName: String, accountName: String) : Boolean {
        return remoteAccountDatasource.addFriend(friendName, accountName)
    }

    override suspend fun deleteFriend(friendName: String, accountName: String) : Boolean {
        return remoteAccountDatasource.deleteFriend(friendName, accountName)
    }

    override suspend fun addComment(comment: Comment, accountName: String) : Boolean {
        return remoteAccountDatasource.addComment(
            mapper.commentMapper.mapToDtoModel(comment),
            accountName
        )
    }

    override suspend fun deleteComment(comment: Comment, accountName: String) : Boolean {
        return remoteAccountDatasource.deleteComment(
            mapper.commentMapper.mapToDtoModel(comment),
            accountName
        )
    }

    override suspend fun registerAccount(account: Profile): Boolean {
        return remoteAccountDatasource.registerAccount(mapper.mapToAccountDtoModel(account))
    }

    override suspend fun getProfileData(name: String): Profile {
        return mapper.map(remoteAccountDatasource.getProfileData(name))
    }

    override suspend fun getAccountListByName(name: String): List<Profile> {
        return remoteAccountDatasource.getAccountListByName(name).map { mapper.map(it) }
    }

}