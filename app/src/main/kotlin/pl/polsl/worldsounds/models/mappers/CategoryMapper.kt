package pl.polsl.worldsounds.models.mappers

import pl.polsl.worldsounds.domain.models.AssetsCountModel
import pl.polsl.worldsounds.domain.models.CategoryModel
import pl.polsl.worldsounds.models.AssetsCountData
import pl.polsl.worldsounds.models.CategoryData
import pl.polsl.worldsounds.utils.FileHelper


internal fun CategoryModel.toData(): CategoryData = CategoryData(
    id = id,
    name = name,
    image = FileHelper.buildFile(name, imageName),
    assetsCounts = assetsCountModel.toData(),
)

private fun AssetsCountModel.toData(): AssetsCountData = AssetsCountData(
    audios = audios,
    images = images,
)