package com.example.sgma.domain.profile

import android.media.tv.TvContract.Programs
import com.example.sgma.data.entity.account.AccountDtoModel

interface ProfileRepository {
    suspend fun changeImage(id: Int, accountName: String) : Boolean

    suspend fun changeName(name: String, accountName: String) : Boolean

    suspend fun changeDescription(description: String, accountName: String) : Boolean

    suspend fun addFriend(friendName: String, accountName: String) : Boolean

    suspend fun deleteFriend(friendName: String, accountName: String) : Boolean

    suspend fun addComment(comment: Comment, accountName: String) : Boolean

    suspend fun deleteComment(comment: Comment, accountName: String): Boolean

    suspend fun registerAccount(account : Profile) : Boolean

    suspend fun getProfileData(name: String) : Profile

    suspend fun getAccountListByName(name: String): List<Profile>
}