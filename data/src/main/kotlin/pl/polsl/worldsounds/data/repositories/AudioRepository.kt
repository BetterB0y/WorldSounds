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

    suspend fun getRandomAudios(categoryId: Long): List<AudioModel> {
        return dao.getRandomAudios(categoryId).map { it.toModel() }
    }

    suspend fun getAnswerAudio(fileName: String): AudioModel {
        //TODO error handling
        return dao.getCorrectAnswer(fileName)?.toModel() ?: throw Exception("Audio not found")
    }
}