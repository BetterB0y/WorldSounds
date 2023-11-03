package pl.polsl.worldsounds.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import pl.polsl.worldsounds.data.database.models.AudioEntity

@Dao
internal interface AudioDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(audio: AudioEntity): Long
}