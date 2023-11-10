package pl.polsl.worldsounds.domain.usecases

import kotlinx.coroutines.flow.Flow
import pl.polsl.worldsounds.domain.base.UseCase
import pl.polsl.worldsounds.domain.models.BestScoreModel
import pl.polsl.worldsounds.domain.models.GameModeModel

interface ObserveScoresWithGameModeUseCase : UseCase<Flow<GameModeModel>, Flow<List<BestScoreModel>>>
