package pl.polsl.worldsounds.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pl.polsl.worldsounds.data.database.models.AudioEntity

@Dao
internal interface AudioDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(audio: AudioEntity): Long

    @Query("SELECT * FROM audios WHERE category_id = :categoryId ORDER BY RANDOM() LIMIT 4")
    suspend fun getRandomAudios(categoryId: Long): List<AudioEntity>

    @Query("SELECT * FROM audios WHERE name = :fileName")
    suspend fun getCorrectAnswer(fileName: String): AudioEntity?
}