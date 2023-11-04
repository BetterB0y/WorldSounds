package pl.polsl.worldsounds.domain.usecases

import pl.polsl.worldsounds.domain.base.SuspendUseCase
import pl.polsl.worldsounds.domain.models.CategoryModel

interface GetCategoryUseCase : SuspendUseCase<Unit, CategoryModel>