package pl.polsl.worldsounds.data.usecases

import pl.polsl.worldsounds.data.repositories.ScoresRepository
import pl.polsl.worldsounds.data.settings.Settings
import pl.polsl.worldsounds.domain.usecases.SaveScoreUseCase

internal class SaveScoreUseCaseImpl(private val settings: Settings, private val scoresRepository: ScoresRepository) :
    SaveScoreUseCase {
    override suspend fun invoke(input: SaveScoreUseCase.Params) {
        val playerName = settings.getUsername()
        val gameMode = settings.getGameMode()
        scoresRepository.saveScore(playerName, input.score, gameMode, input.categoryName)
    }
}