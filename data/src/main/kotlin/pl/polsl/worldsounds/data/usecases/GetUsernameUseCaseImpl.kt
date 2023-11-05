package pl.polsl.worldsounds.data.usecases

import pl.polsl.worldsounds.data.settings.Settings
import pl.polsl.worldsounds.domain.usecases.GetUsernameUseCase

internal class GetUsernameUseCaseImpl(private val settings: Settings) : GetUsernameUseCase {
    override suspend fun invoke(input: Unit): String {
        return settings.getUsername()
    }
}