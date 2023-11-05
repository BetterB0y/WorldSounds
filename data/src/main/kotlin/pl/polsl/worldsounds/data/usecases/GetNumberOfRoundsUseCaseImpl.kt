package pl.polsl.worldsounds.data.usecases

import pl.polsl.worldsounds.data.settings.Settings
import pl.polsl.worldsounds.domain.usecases.GetNumberOfRoundsUseCase

internal class GetNumberOfRoundsUseCaseImpl(private val settings: Settings) : GetNumberOfRoundsUseCase {
    override suspend fun invoke(input: Unit): Int {
        return settings.getNumberOfRounds()
    }
}
