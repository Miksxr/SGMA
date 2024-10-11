package com.example.sgma.data.datasource.local

import android.content.Context
import android.provider.ContactsContract.Data
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [], version = 0)
abstract class MediaDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE : MediaDatabase? = null
        private val DB_NAME : String = "media_db"

        fun newInstance(context : Context) : MediaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    MediaDatabase::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}