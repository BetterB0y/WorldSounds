package pl.polsl.worldsounds.data.models.mappers

import pl.polsl.worldsounds.data.database.models.CategoryEntity
import pl.polsl.worldsounds.domain.models.CategoryModel


internal fun CategoryEntity.toModel(imageName: String): CategoryModel = CategoryModel(
    name = name,
    imageName = imageName
)