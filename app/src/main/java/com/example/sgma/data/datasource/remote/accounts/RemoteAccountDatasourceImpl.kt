package com.example.sgma.data.datasource.remote.accounts

import com.example.sgma.data.datasource.remote.CollectionNames
import com.example.sgma.data.datasource.remote.Firecloud
import com.example.sgma.data.entity.account.AccountDtoModel
import com.example.sgma.data.entity.account.CommentsDtoModel
import com.google.firebase.firestore.FieldValue
import kotlinx.coroutines.tasks.await

class RemoteAccountDatasourceImpl : RemoteAccountDatasource {

    private val db : Firecloud = Firecloud(CollectionNames.accounts)

    override suspend fun changeImage(id: Int, accountName: String) {
        val newData = mapOf(
            FieldKeys.image to id
        )
        db.findDocument(accountName).update(newData)
    }

    override suspend fun changeName(name: String, accountName: String) {
        val newData = mapOf(
            FieldKeys.name to name
        )
        db.findDocument(accountName).update(newData)
    }

    override suspend fun changeDescription(description: String, accountName: String) {
        val newData = mapOf(
            FieldKeys.description to description
        )
        db.findDocument(accountName).update(newData)
    }

    override suspend fun addFriend(friendName: String, accountName: String) {
        val newData = mapOf(
            FieldKeys.friends to FieldValue.arrayUnion(friendName)
        )
        db.findDocument(accountName).update(newData)
    }

    override suspend fun deleteFriend(friendName: String, accountName: String) {
        val newData = mapOf(
            FieldKeys.friends to FieldValue.arrayRemove(friendName)
        )
        db.findDocument(accountName).update(newData)
    }

    override suspend fun addComment(comment: CommentsDtoModel, accountName: String) {
        val newData = mapOf(
            FieldKeys.comments to FieldValue.arrayUnion(comment)
        )
        db.findDocument(accountName).update(newData)
    }

    override suspend fun deleteComment(comment: CommentsDtoModel, accountName: String) {
        val newData = mapOf(
            FieldKeys.comments to FieldValue.arrayRemove(comment)
        )
        db.findDocument(accountName).update(newData)
    }

    override suspend fun getProfileData(name: String): AccountDtoModel {
        var account : AccountDtoModel? = null
        account = db.findDocument(name).get().await().toObject(AccountDtoModel::class.java)
        if (account != null) {
            return account as AccountDtoModel
        }
        else {
            return AccountDtoModel()
        }
    }

}