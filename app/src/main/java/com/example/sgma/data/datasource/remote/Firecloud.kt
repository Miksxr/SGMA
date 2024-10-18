package com.example.sgma.data.datasource.remote

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


class Firecloud(
    private val collection : String
) {
    val db : CollectionReference
    init {
        db = Firebase.firestore.collection(collection)
    }

    fun findDocument(name: String) : DocumentReference {
        return db.document(name)
    }

    suspend fun softFindDocument(name: String) : QuerySnapshot {
        return db.whereGreaterThanOrEqualTo(FieldPath.documentId(), name)
            .whereLessThan(FieldPath.documentId(), name + "\uf8ff")
            .get().await()
    }

    companion object {
        private var INSTANCE : Firecloud? = null

        fun newInstance(collection: String) : Firecloud {
            return INSTANCE ?: synchronized(this) {
                val instance = Firecloud(collection)
                INSTANCE = instance
                instance
            }
        }
    }
}