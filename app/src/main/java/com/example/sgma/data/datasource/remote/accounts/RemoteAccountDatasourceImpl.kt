package com.example.sgma.data.datasource.remote.accounts

import com.example.sgma.data.datasource.remote.CollectionNames
import com.example.sgma.data.datasource.remote.Firecloud
import com.example.sgma.data.entity.account.AccountDtoModel
import com.example.sgma.data.entity.account.CommentsDtoModel
import com.google.firebase.firestore.FieldValue
import kotlinx.coroutines.tasks.await

class RemoteAccountDatasourceImpl : RemoteAccountDatasource {

    private val db : Firecloud = Firecloud(CollectionNames.accounts)

    private suspend fun updateData(accountName: String, data : Map<String, Any>) : Boolean {
        try {
            db.findDocument(accountName).update(data).await()
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override suspend fun changeImage(id: Int, accountName: String) : Boolean {
        val newData = mapOf(
            FieldKeys.image to id
        )
        return updateData(accountName, newData)
    }

    override suspend fun changeName(name: String, accountName: String) : Boolean {
        val newData = mapOf(
            FieldKeys.name to name
        )
        return updateData(accountName, newData)
    }

    override suspend fun changeDescription(description: String, accountName: String) : Boolean {
        val newData = mapOf(
            FieldKeys.description to description
        )
        return updateData(accountName, newData)
    }

    override suspend fun addFriend(friendName: String, accountName: String) : Boolean {
        val newData = mapOf(
            FieldKeys.friends to FieldValue.arrayUnion(friendName)
        )
        return updateData(accountName, newData)
    }

    override suspend fun deleteFriend(friendName: String, accountName: String) : Boolean {
        val newData = mapOf(
            FieldKeys.friends to FieldValue.arrayRemove(friendName)
        )
        return updateData(accountName, newData)
    }

    override suspend fun addComment(comment: CommentsDtoModel, accountName: String) : Boolean {
        val newData = mapOf(
            FieldKeys.comments to FieldValue.arrayUnion(comment)
        )
        return updateData(accountName, newData)
    }

    override suspend fun deleteComment(comment: CommentsDtoModel, accountName: String) : Boolean {
        val newData = mapOf(
            FieldKeys.comments to FieldValue.arrayRemove(comment)
        )
        return updateData(accountName, newData)
    }

    override suspend fun getProfileData(name: String): AccountDtoModel {
        var account : AccountDtoModel? = null
        account = db.findDocument(name).get().await().toObject(AccountDtoModel::class.java)
        if (account != null) {
            return account
        }
        else {
            return AccountDtoModel()
        }
    }

}