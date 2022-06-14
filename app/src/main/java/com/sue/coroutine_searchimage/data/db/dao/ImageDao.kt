package com.sue.coroutine_searchimage.data.db.dao

import androidx.room.*
import com.sue.coroutine_searchimage.data.db.entity.ImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(imageEntity: ImageEntity)

    @Query("SELECT * FROM imageentity")
    fun getImages(): Flow<List<ImageEntity>>

    @Delete
    suspend fun deleteImage(imageEntity: ImageEntity)
}