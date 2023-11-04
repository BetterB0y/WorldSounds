package pl.polsl.worldsounds.domain.usecases

import pl.polsl.worldsounds.domain.base.SuspendUseCase
import pl.polsl.worldsounds.domain.models.GameModeModel
import pl.polsl.worldsounds.domain.models.RoundAssetsModel

interface GetRoundAssetsUseCase : SuspendUseCase<GetRoundAssetsUseCase.Params, RoundAssetsModel> {
    data class Params(
        val categoryId: Long,
        val gameMode: GameModeModel
    )
}