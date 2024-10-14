package com.example.sgma.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sgma.data.entity.MediaDBModel


// локальная база данных для сохранения элементов помеченных пользователем
@Database(entities = [MediaDBModel::class], version = 1)
abstract class MediaDatabase : RoomDatabase() {

    abstract fun getMediaItemDao() : MediaItemDao

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