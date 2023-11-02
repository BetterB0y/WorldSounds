package pl.polsl.worldsounds.data.usecase

import pl.polsl.worldsounds.data.settings.Settings
import pl.polsl.worldsounds.domain.models.GameModeModel
import pl.polsl.worldsounds.domain.usecase.SaveGameModeUseCase

internal class SaveGameModeUseCaseImpl(private val settings: Settings) : SaveGameModeUseCase {
    override suspend fun invoke(input: GameModeModel) {
        settings.setGameMode(input)
    }
}