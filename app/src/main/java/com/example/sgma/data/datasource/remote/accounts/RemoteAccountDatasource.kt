package com.example.sgma.data.datasource.remote.accounts

import com.example.sgma.data.entity.account.AccountDtoModel
import com.example.sgma.data.entity.account.CommentsDtoModel

interface RemoteAccountDatasource {
    suspend fun changeImage(id: Int, accountName: String) : Boolean

    suspend fun changeName(name: String, accountName: String) : Boolean

    suspend fun changeDescription(description: String, accountName: String) : Boolean

    suspend fun addFriend(friendName: String, accountName: String) : Boolean

    suspend fun deleteFriend(friendName: String, accountName: String) : Boolean

    suspend fun addComment(comment: CommentsDtoModel, accountName: String) : Boolean

    suspend fun deleteComment(comment: CommentsDtoModel,  accountName: String) : Boolean

    suspend fun getProfileData(name: String) : AccountDtoModel

    suspend fun getAccountListByName(name: String) : List<AccountDtoModel>

    suspend fun registerAccount(account: AccountDtoModel) : Boolean
}