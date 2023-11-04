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
        // TODO error handling
        return dao.getCategoryImage(categoryId)?.fileName ?: ""
    }

    suspend fun getRandomImages(categoryId: Long): List<ImageModel> {
        return dao.getRandomImages(categoryId).map { it.toModel() }
    }

    suspend fun getAnswerImage(fileName: String): ImageModel {
        // TODO error handling
        return dao.getCorrectAnswer(fileName)?.toModel() ?: throw Exception("Image not found")
    }
}