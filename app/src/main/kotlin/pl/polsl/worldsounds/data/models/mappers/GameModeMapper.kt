package pl.polsl.worldsounds.data.models.mappers

import pl.polsl.worldsounds.data.models.GameModeUi
import pl.polsl.worldsounds.domain.models.GameModeModel


internal fun GameModeUi.toModel(): GameModeModel {
    return when (this) {
        GameModeUi.SoundToImage -> GameModeModel.SoundToImage
        GameModeUi.ImageToSound -> GameModeModel.ImageToSound
    }
}