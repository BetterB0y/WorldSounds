package pl.polsl.worldsounds.data.repositories

import pl.polsl.worldsounds.data.database.dao.AudioDao
import pl.polsl.worldsounds.data.database.models.AudioEntity

internal class AudioRepository(
    private val dao: AudioDao
) {
    suspend fun addAudio(fileName: String, categoryId: Long) {
        dao.insert(AudioEntity.new(fileName, categoryId))
    }
}