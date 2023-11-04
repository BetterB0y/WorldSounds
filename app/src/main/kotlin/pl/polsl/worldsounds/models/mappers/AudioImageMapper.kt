package pl.polsl.worldsounds.models.mappers

import pl.polsl.worldsounds.domain.models.AudioModel
import pl.polsl.worldsounds.domain.models.ImageModel
import pl.polsl.worldsounds.models.AudioData
import pl.polsl.worldsounds.models.ImageData
import pl.polsl.worldsounds.utils.FileHelper

fun AudioModel.toData(category: String): AudioData {
    return AudioData(
        id = id,
        file = FileHelper.buildFile(category, fileName),
    )
}

fun ImageModel.toData(category: String): ImageData {
    return ImageData(
        id = id,
        file = FileHelper.buildFile(category, fileName),
    )
}