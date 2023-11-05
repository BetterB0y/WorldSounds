package pl.polsl.worldsounds.data.usecases

import pl.polsl.worldsounds.data.settings.Settings
import pl.polsl.worldsounds.domain.usecases.SaveNumberOfRoundsUseCase

internal class SaveNumberOfRoundsUseCaseImpl(private val settings: Settings) : SaveNumberOfRoundsUseCase {
    override suspend fun invoke(input: Int) {
        settings.setNumberOfRounds(input)
    }
}
