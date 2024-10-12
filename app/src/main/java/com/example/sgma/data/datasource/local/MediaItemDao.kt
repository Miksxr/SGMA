package com.example.sgma.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sgma.data.entity.Media

// dao интерфейс проводит взаимодействие с локальной базой данных
@Dao
interface MediaItemDao {

    @Insert
    fun insertMediaItem()

    @Query("SELECT * FROM media")
    fun getAllMedia() : List<Media>

    @Query("UPDATE media SET statusType = :type WHERE id = :id")
    fun updateStatusType(type: String, id: Int)
}