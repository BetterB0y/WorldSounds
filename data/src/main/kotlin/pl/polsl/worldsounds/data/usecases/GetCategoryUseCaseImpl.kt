package pl.polsl.worldsounds.data.usecases

import pl.polsl.worldsounds.data.repositories.CategoryRepository
import pl.polsl.worldsounds.data.settings.Settings
import pl.polsl.worldsounds.domain.models.CategoryModel
import pl.polsl.worldsounds.domain.usecases.GetCategoryUseCase

internal class GetCategoryUseCaseImpl(
    private val settings: Settings,
    private val categoryRepository: CategoryRepository
) :
    GetCategoryUseCase {
    override suspend fun invoke(input: Unit): CategoryModel {
        val categoryId = settings.getCategoryId()
        return categoryRepository.getCategoryById(categoryId)
    }
}