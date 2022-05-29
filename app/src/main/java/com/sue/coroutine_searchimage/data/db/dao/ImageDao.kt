package com.sue.coroutine_searchimage.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sue.coroutine_searchimage.data.db.entity.ImageEntity

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(imageEntity: ImageEntity)

    @Query("SELECT * FROM imageentity")
    suspend fun getImages(): List<ImageEntity>

    @Query("DELETE FROM imageentity WHERE id=:id")
    suspend fun deleteImage(id: Int)
}