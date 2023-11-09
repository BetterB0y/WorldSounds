package pl.polsl.worldsounds.models.mappers

import pl.polsl.worldsounds.domain.models.RoundAssetsModel
import pl.polsl.worldsounds.models.RoundAssetsData


fun RoundAssetsModel.toData(category: String): RoundAssetsData {
    return when (this) {
        is RoundAssetsModel.OnePicture -> {
            RoundAssetsData.OnePicture(
                image = image.toData(category),
                audios = audios.map { it.toData(category) }
            )
        }

        is RoundAssetsModel.OneSound -> {
            RoundAssetsData.OneSound(
                audio = audio.toData(category),
                images = images.map { it.toData(category) }
            )
        }
    }
}