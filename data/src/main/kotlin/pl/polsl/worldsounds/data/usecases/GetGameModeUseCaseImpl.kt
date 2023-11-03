package pl.polsl.worldsounds.data.usecases

import pl.polsl.worldsounds.data.settings.Settings
import pl.polsl.worldsounds.domain.models.GameModeModel
import pl.polsl.worldsounds.domain.usecases.GetGameModeUseCase

internal class GetGameModeUseCaseImpl(private val settings: Settings) : GetGameModeUseCase {
    override suspend fun invoke(input: Unit): GameModeModel {
        return settings.getGameMode()
    }
}