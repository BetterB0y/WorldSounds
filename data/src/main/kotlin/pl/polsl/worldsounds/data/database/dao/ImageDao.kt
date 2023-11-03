package pl.polsl.worldsounds.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pl.polsl.worldsounds.data.database.models.ImageEntity

@Dao
internal interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(image: ImageEntity): Long

    @Query("SELECT name FROM images WHERE category_id = :categoryId ORDER BY name ASC LIMIT 1")
    suspend fun getCategoryImage(categoryId: Long): String?
}