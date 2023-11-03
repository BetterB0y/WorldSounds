package pl.polsl.worldsounds.models.mappers

import pl.polsl.worldsounds.domain.models.CategoryModel
import pl.polsl.worldsounds.models.CategoryData
import pl.polsl.worldsounds.utils.FileHelper


internal fun CategoryModel.toData(): CategoryData = CategoryData(
    name = name,
    image = FileHelper.buildFile(name, imageName)
)