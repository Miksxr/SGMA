package com.example.sgma.data.datasource.remote.comment

import com.example.sgma.data.datasource.remote.CollectionNames
import com.example.sgma.data.datasource.remote.Firestore
import com.example.sgma.data.entity.account.CommentsDtoModel
import com.google.firebase.firestore.FieldValue
import kotlinx.coroutines.tasks.await

class RemoteCommentDatasourceImpl : RemoteCommentDatasource {

    private val db : Firestore

    init {
        db = Firestore(CollectionNames.comments)
    }

    override suspend fun getCommnets(filmId: Int): List<CommentsDtoModel> {
        return db.findDocument(filmId.toString())
                .get().await().toObject(Holder::class.java)?.comments ?: listOf()
    }

    override suspend fun addComment(filmId: Int, comment: CommentsDtoModel) : Boolean {
        val documentRef = db.findDocument(filmId.toString())
        return try {
            val documentSnapshot = documentRef.get().await()
            val comments = documentSnapshot.get("comments") as? List<CommentsDtoModel> ?: emptyList()
            val updatedComments = comments.toMutableList().plus(comment)
            documentRef.update("comments", updatedComments).isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    private class Holder {
        var comments : List<CommentsDtoModel> = listOf()
    }

    private object FieldKeys {
        val comments = "comments"
        val account_name = "account_name"
        val comment = "comment"
        val sgma_rating = "sgma_rating"
    }
}