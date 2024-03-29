package pl.polsl.worldsounds.data.repositories

import pl.polsl.worldsounds.data.database.dao.ImageDao
import pl.polsl.worldsounds.data.database.models.ImageEntity
import pl.polsl.worldsounds.data.database.models.mappers.toModel
import pl.polsl.worldsounds.domain.models.ImageModel

internal class ImageRepository(
    private val dao: ImageDao
) {
    suspend fun addImage(fileName: String, extension: String, categoryId: Long) {
        dao.insert(ImageEntity.new(fileName, extension, categoryId))
    }

    suspend fun getCategoryImage(categoryId: Long): String {
        return dao.getCategoryImage(categoryId)?.fileName ?: ""
    }

    suspend fun getImageAssets(categoryId: Long, answerFilename: String): List<ImageModel> {
        return dao.getAssets(categoryId, answerFilename).map { it.toModel() }.shuffled()
    }

    suspend fun getRandomAnswerNotIn(categoryId: Long, answers: Set<Long>): ImageModel {
        return dao.get1RandomNotIn(categoryId, answers)?.toModel() ?: throw Exception("Image not found")
    }
}