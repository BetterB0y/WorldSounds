package pl.polsl.worldsounds.data.usecases

import pl.polsl.worldsounds.data.settings.Settings
import pl.polsl.worldsounds.domain.usecases.SaveUsernameUseCase

internal class SaveUsernameUseCaseImpl(private val settings: Settings) : SaveUsernameUseCase {
    override suspend fun invoke(input: String) {
        settings.setUsername(input)
    }
}