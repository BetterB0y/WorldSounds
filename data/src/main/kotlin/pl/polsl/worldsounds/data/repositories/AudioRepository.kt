package pl.polsl.worldsounds.data.repositories

import pl.polsl.worldsounds.data.database.dao.AudioDao
import pl.polsl.worldsounds.data.database.models.AudioEntity
import pl.polsl.worldsounds.data.database.models.mappers.toModel
import pl.polsl.worldsounds.domain.models.AudioModel

internal class AudioRepository(
    private val dao: AudioDao
) {
    suspend fun addAudio(fileName: String, extension: String, categoryId: Long) {
        dao.insert(AudioEntity.new(fileName, extension, categoryId))
    }

    suspend fun getAudioAssets(categoryId: Long, answerFilename: String): List<AudioModel> {
        return dao.getAssets(categoryId, answerFilename).map { it.toModel() }.shuffled()
    }

    suspend fun getRandomAnswerNotIn(categoryId: Long, answers: Set<Long>): AudioModel {
        // TODO error handling
        return dao.get1RandomNotIn(categoryId, answers)?.toModel() ?: throw Exception("Audio not found")
    }
}