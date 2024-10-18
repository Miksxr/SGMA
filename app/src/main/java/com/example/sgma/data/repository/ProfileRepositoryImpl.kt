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
    override suspend fun changeImage(id: Int, accountName: String) {
        remoteAccountDatasource.changeImage(id, accountName)
    }

    override suspend fun changeName(name: String, accountName: String) {

    }

    override suspend fun changeDescription(description: String, accountName: String) {

    }

    override suspend fun addFriend(friendName: String, accountName: String) {

    }

    override suspend fun deleteFriend(friendName: String, accountName: String) {

    }

    override suspend fun addComment(comment: Comment, accountName: String) {

    }

    override suspend fun deleteComment(comment: Comment, accountName: String) {

    }


    override suspend fun getProfileData(name: String): Profile {
        return mapper.map(remoteAccountDatasource.getProfileData(name))
    }

}