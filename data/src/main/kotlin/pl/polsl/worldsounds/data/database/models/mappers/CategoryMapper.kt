package pl.polsl.worldsounds.data.database.models.mappers

import pl.polsl.worldsounds.data.database.models.views.CategoryCountView
import pl.polsl.worldsounds.domain.models.AssetsCountModel
import pl.polsl.worldsounds.domain.models.CategoryModel


internal fun CategoryCountView.toModel(imageName: String): CategoryModel = CategoryModel(
    id = id,
    name = name,
    imageName = imageName,
    assetsCountModel = AssetsCountModel(
        audios = audioCount,
        images = imageCount,
    )
)
