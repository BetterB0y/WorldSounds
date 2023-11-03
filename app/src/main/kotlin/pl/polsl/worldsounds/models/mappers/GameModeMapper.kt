package pl.polsl.worldsounds.models.mappers

import pl.polsl.worldsounds.domain.models.GameModeModel
import pl.polsl.worldsounds.models.GameModeData


internal fun GameModeData.toModel(): GameModeModel {
    return when (this) {
        GameModeData.OnePicture -> GameModeModel.OnePicture
        GameModeData.OneSound -> GameModeModel.OneSound
    }
}