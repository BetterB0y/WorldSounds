package pl.polsl.worldsounds.data.usecases

import pl.polsl.worldsounds.data.repositories.CategoryRepository
import pl.polsl.worldsounds.domain.usecases.ScanFolderWithAssetsUseCase

internal class ScanFolderWithAssetsUseCaseImpl(private val categoryRepository: CategoryRepository) :
    ScanFolderWithAssetsUseCase {
    override suspend fun invoke(input: Unit) {
        categoryRepository.scanFolderWithAssets()
    }
}