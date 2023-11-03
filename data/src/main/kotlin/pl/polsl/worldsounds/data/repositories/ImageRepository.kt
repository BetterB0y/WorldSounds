package pl.polsl.worldsounds.data.repositories

import pl.polsl.worldsounds.data.database.dao.ImageDao
import pl.polsl.worldsounds.data.database.models.ImageEntity

internal class ImageRepository(
    private val dao: ImageDao
) {
    suspend fun addImage(fileName: String, categoryId: Long) {
        dao.insert(ImageEntity.new(fileName, categoryId))
    }
}