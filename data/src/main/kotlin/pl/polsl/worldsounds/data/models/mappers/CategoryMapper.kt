package pl.polsl.worldsounds.data.models.mappers

import pl.polsl.worldsounds.data.database.models.CategoryEntity
import pl.polsl.worldsounds.domain.models.CategoryModel


internal fun CategoryEntity.toModel(imageName: String): CategoryModel = CategoryModel(
    id = id,
    name = name,
    imageName = imageName
)