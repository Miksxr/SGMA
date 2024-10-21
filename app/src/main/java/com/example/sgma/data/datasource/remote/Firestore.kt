package com.example.sgma.data.datasource.remote

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


class Firestore(
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
            .whereLessThan(FieldPath.documentId(), "\uf8ff" + name + "\uf8ff")
            .get().await()
    }

    fun createDocument(docName: String, data : Any) : Boolean {
        return db.document(docName).set(data).isSuccessful
    }

    companion object {
        private var INSTANCE : Firestore? = null

        fun newInstance(collection: String) : Firestore {
            return INSTANCE ?: synchronized(this) {
                val instance = Firestore(collection)
                INSTANCE = instance
                instance
            }
        }
    }
}