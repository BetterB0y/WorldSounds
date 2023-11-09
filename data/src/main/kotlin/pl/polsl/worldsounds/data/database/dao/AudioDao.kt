@file:Suppress("FunctionName")

package pl.polsl.worldsounds.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import pl.polsl.worldsounds.data.database.models.AudioEntity

@Dao
internal interface AudioDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(audio: AudioEntity): Long

    @Query("SELECT * FROM audios WHERE id NOT IN (:answers) ORDER BY RANDOM() LIMIT 1")
    suspend fun get1RandomNotIn(answers: Set<Long>): AudioEntity?

    @Transaction
    suspend fun getAssets(categoryId: Long, answerFilename: String): List<AudioEntity> {
        val randomAudios = _get3RandomExceptAnswer(categoryId, answerFilename)
        val answerAudio = _getAudioBy(answerFilename) ?: throw Exception("Audio not found")
        return randomAudios + answerAudio
    }

    @Query("SELECT * FROM audios WHERE category_id = :categoryId AND name != :answerFilename ORDER BY RANDOM() LIMIT 3")
    suspend fun _get3RandomExceptAnswer(categoryId: Long, answerFilename: String): List<AudioEntity>

    @Query("SELECT * FROM audios WHERE name = :name")
    suspend fun _getAudioBy(name: String): AudioEntity?
}