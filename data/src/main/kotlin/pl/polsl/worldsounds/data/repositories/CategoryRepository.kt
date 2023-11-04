package pl.polsl.worldsounds.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.polsl.worldsounds.data.database.dao.CategoryDao
import pl.polsl.worldsounds.data.database.models.CategoryEntity
import pl.polsl.worldsounds.data.database.models.mappers.toModel
import pl.polsl.worldsounds.domain.base.Config
import pl.polsl.worldsounds.domain.models.CategoryModel
import java.io.File

internal class CategoryRepository(
    private val dao: CategoryDao,
    private val audioRepository: AudioRepository,
    private val imageRepository: ImageRepository
) {
    private val appFolder = File(Config.basePath)

    suspend fun scanFolderWithAssets() {
        dao.deleteAll()

        appFolder.listFiles()?.forEach { categoryDir ->
            if (categoryDir.isDirectory) {
                val categoryId = dao.insert(CategoryEntity.new(categoryDir.name))

                val filesList = categoryDir.listFiles { file ->
                    file.extension == "mp3" || file.extension == "jpg"
                }

                filesList?.forEach { file ->
                    if (file.extension == "mp3") {
                        audioRepository.addAudio(file.nameWithoutExtension, file.extension, categoryId)
                    } else {
                        imageRepository.addImage(file.nameWithoutExtension, file.extension, categoryId)
                    }
                }
            }
        }
    }

    fun observeCategories(): Flow<List<CategoryModel>> {
        return dao.observeAll().map {
            it.map { entity ->
                val imageName = imageRepository.getCategoryImage(entity.id)
                entity.toModel(imageName)
            }
        }
    }

    suspend fun getCategoryById(id: Long): CategoryModel = dao.getById(id).let { entity ->
        val imageName = imageRepository.getCategoryImage(entity.id)
        return entity.toModel(imageName)
    }
}