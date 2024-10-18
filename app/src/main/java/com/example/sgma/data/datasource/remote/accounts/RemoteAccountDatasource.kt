package com.example.sgma.data.datasource.remote.accounts

import com.example.sgma.data.entity.account.AccountDtoModel
import com.example.sgma.data.entity.account.CommentsDtoModel

interface RemoteAccountDatasource {
    suspend fun changeImage(id: Int, accountName: String)

    suspend fun changeName(name: String, accountName: String)

    suspend fun changeDescription(description: String, accountName: String)

    suspend fun addFriend(friendName: String, accountName: String)

    suspend fun deleteFriend(friendName: String, accountName: String)

    suspend fun addComment(comment: CommentsDtoModel, accountName: String)

    suspend fun deleteComment(comment: CommentsDtoModel,  accountName: String)

    suspend fun getProfileData(name: String) : AccountDtoModel
}