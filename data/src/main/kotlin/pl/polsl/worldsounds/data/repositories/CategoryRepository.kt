package pl.polsl.worldsounds.data.repositories

import pl.polsl.worldsounds.data.database.dao.CategoryDao
import pl.polsl.worldsounds.data.database.models.CategoryEntity
import pl.polsl.worldsounds.domain.base.Config
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
                    if (file.name.endsWith(".mp3")) {
                        audioRepository.addAudio(file.name, categoryId)
                    } else {
                        imageRepository.addImage(file.name, categoryId)
                    }
                }
            }
        }
    }


}