package com.example.sgma.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sgma.data.entity.MediaDBModel

// dao интерфейс проводит взаимодействие с локальной базой данных
@Dao
interface MediaItemDao {

    @Insert
    fun insertMediaItem(mediaDBModel: MediaDBModel)

    @Query("SELECT * FROM media WHERE id = :id LIMIT 1")
    fun checkMediaInDatabase(id: Int) : MediaDBModel

    @Query("SELECT * FROM media")
    fun getAllMedia() : List<MediaDBModel>

    @Query("UPDATE media SET statusType = :type WHERE id = :id")
    fun updateStatusType(type: String, id: Int)

    @Query("SELECT * FROM media WHERE statusType = :statusType")
    fun selectByType(statusType: String) : List<MediaDBModel>
}