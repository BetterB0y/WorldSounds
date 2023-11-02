package pl.polsl.worldsounds.domain.usecase

import pl.polsl.worldsounds.domain.base.SuspendUseCase
import pl.polsl.worldsounds.domain.models.GameModeModel

interface GetGameModeUseCase : SuspendUseCase<Unit, GameModeModel>