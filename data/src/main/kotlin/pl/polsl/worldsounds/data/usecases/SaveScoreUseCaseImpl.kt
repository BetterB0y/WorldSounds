package pl.polsl.worldsounds.data.usecases

import pl.polsl.worldsounds.data.repositories.GameRepository
import pl.polsl.worldsounds.data.settings.Settings
import pl.polsl.worldsounds.domain.usecases.SaveScoreUseCase

internal class SaveScoreUseCaseImpl(private val settings: Settings, private val gameRepository: GameRepository) :
    SaveScoreUseCase {
    override suspend fun invoke(input: SaveScoreUseCase.Params) {
        val playerName = settings.getUsername()
        gameRepository.saveScore(playerName, input.score, input.categoryId)
    }
}