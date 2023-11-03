package pl.polsl.worldsounds.data.usecases

import kotlinx.coroutines.flow.Flow
import pl.polsl.worldsounds.data.repositories.CategoryRepository
import pl.polsl.worldsounds.domain.models.CategoryModel
import pl.polsl.worldsounds.domain.usecases.ObserveCategoriesUseCase

internal class ObserveCategoriesUseCaseImpl(private val categoryRepository: CategoryRepository) :
    ObserveCategoriesUseCase {
    override fun invoke(input: Unit): Flow<List<CategoryModel>> {
        return categoryRepository.observeCategories()
    }
}