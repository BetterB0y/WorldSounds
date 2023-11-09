@file:Suppress("FunctionName")

package pl.polsl.worldsounds.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import pl.polsl.worldsounds.data.database.models.ImageEntity

@Dao
internal interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(image: ImageEntity): Long

    @Query("SELECT * FROM images WHERE category_id = :categoryId ORDER BY name ASC LIMIT 1")
    suspend fun getCategoryImage(categoryId: Long): ImageEntity?

    @Query("SELECT * FROM images WHERE category_id = :categoryId AND id NOT IN (:answers) ORDER BY RANDOM() LIMIT 1")
    suspend fun get1RandomNotIn(categoryId: Long, answers: Set<Long>): ImageEntity?

    @Transaction
    suspend fun getAssets(categoryId: Long, answerFilename: String): List<ImageEntity> {
        val randomImages = _get3RandomExceptAnswer(categoryId, answerFilename)
        val answerImage = _getImageBy(answerFilename) ?: throw Exception("Image not found")
        return randomImages + answerImage
    }

    @Query("SELECT * FROM images WHERE category_id = :categoryId AND name != :answerFilename ORDER BY RANDOM() LIMIT 3")
    suspend fun _get3RandomExceptAnswer(categoryId: Long, answerFilename: String): List<ImageEntity>

    @Query("SELECT * FROM images WHERE name = :name")
    suspend fun _getImageBy(name: String): ImageEntity?
}