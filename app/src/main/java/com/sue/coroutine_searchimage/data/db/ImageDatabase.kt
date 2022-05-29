package com.sue.coroutine_searchimage.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sue.coroutine_searchimage.data.db.dao.ImageDao
import com.sue.coroutine_searchimage.data.db.entity.ImageEntity

@Database(
    entities = [ImageEntity::class],
    version = 1
)
abstract class ImageDatabase: RoomDatabase() {
    abstract val imageDao: ImageDao
}