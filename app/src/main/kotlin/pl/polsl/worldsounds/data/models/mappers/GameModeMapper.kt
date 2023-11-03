package pl.polsl.worldsounds.data.models.mappers

import pl.polsl.worldsounds.data.models.GameModeUi
import pl.polsl.worldsounds.domain.models.GameModeModel


internal fun GameModeUi.toModel(): GameModeModel {
    return when (this) {
        GameModeUi.OnePicture -> GameModeModel.OnePicture
        GameModeUi.OneSound -> GameModeModel.OneSound
    }
}