package pl.polsl.worldsounds.data.usecases

import kotlinx.coroutines.flow.Flow
import pl.polsl.worldsounds.data.repositories.ScoresRepository
import pl.polsl.worldsounds.domain.models.BestScoreModel
import pl.polsl.worldsounds.domain.models.GameModeModel
import pl.polsl.worldsounds.domain.usecases.ObserveScoresWithGameModeUseCase


internal class ObserveScoresWithGameModeUseCaseImpl(private val scoresRepository: ScoresRepository) :
    ObserveScoresWithGameModeUseCase {
    override fun invoke(input: Flow<GameModeModel>): Flow<List<BestScoreModel>> {
        return scoresRepository.observeScoresWithGameMode(input)
    }
}