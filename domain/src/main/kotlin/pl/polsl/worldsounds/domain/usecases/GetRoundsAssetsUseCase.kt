package pl.polsl.worldsounds.domain.usecases

import pl.polsl.worldsounds.domain.base.SuspendUseCase
import pl.polsl.worldsounds.domain.models.GameModeModel
import pl.polsl.worldsounds.domain.models.RoundAssetsModel

interface GetRoundsAssetsUseCase : SuspendUseCase<GetRoundsAssetsUseCase.Params, List<RoundAssetsModel>> {
    sealed class Params {
        abstract val categoryId: Long
        abstract val numberOfRounds: Int
        abstract val gameMode: GameModeModel

        data class OneSound(
            override val categoryId: Long,
            override val numberOfRounds: Int,
        ) : Params() {
            override val gameMode: GameModeModel = GameModeModel.OneSound
        }

        data class OnePicture(
            override val categoryId: Long,
            override val numberOfRounds: Int,
        ) : Params() {
            override val gameMode: GameModeModel = GameModeModel.OnePicture
        }
    }
}