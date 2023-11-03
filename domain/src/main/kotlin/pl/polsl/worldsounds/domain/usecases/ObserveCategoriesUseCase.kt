package pl.polsl.worldsounds.domain.usecases

import kotlinx.coroutines.flow.Flow
import pl.polsl.worldsounds.domain.base.UseCase
import pl.polsl.worldsounds.domain.models.CategoryModel

interface ObserveCategoriesUseCase : UseCase<Unit, Flow<List<CategoryModel>>>