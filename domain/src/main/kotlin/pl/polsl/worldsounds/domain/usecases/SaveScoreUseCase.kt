package pl.polsl.worldsounds.domain.usecases

import pl.polsl.worldsounds.domain.base.SuspendUseCase

interface SaveScoreUseCase : SuspendUseCase<SaveScoreUseCase.Params, Unit> {
    data class Params(
        val score: Int,
        val categoryId: Long,
    )
}