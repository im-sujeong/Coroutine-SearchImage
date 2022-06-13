package com.sue.coroutine_searchimage.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sue.coroutine_searchimage.data.db.entity.ImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(imageEntity: ImageEntity)

    @Query("SELECT * FROM imageentity")
    fun getImages(): Flow<List<ImageEntity>>

    @Query("DELETE FROM imageentity WHERE thumbnail=:thumbnail")
    suspend fun deleteImage(thumbnail: String)
}