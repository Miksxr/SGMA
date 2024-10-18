package com.example.sgma.domain.profile

interface ProfileRepository {
    suspend fun changeImage(id: Int, accountName: String)

    suspend fun changeName(name: String, accountName: String)

    suspend fun changeDescription(description: String, accountName: String)

    suspend fun addFriend(friendName: String, accountName: String)

    suspend fun deleteFriend(friendName: String, accountName: String)

    suspend fun addComment(comment: Comment, accountName: String)

    suspend fun deleteComment(comment: Comment, accountName: String)

    suspend fun getProfileData(name: String) : Profile
}