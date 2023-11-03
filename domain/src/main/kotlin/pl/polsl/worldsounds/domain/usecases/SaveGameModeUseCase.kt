package pl.polsl.worldsounds.domain.usecases

import pl.polsl.worldsounds.domain.base.SuspendUseCase
import pl.polsl.worldsounds.domain.models.GameModeModel

interface SaveGameModeUseCase : SuspendUseCase<GameModeModel, Unit>