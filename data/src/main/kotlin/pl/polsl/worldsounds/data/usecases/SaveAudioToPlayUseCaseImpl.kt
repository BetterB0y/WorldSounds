package pl.polsl.worldsounds.data.usecases

import pl.polsl.worldsounds.data.settings.Settings
import pl.polsl.worldsounds.domain.usecases.SaveAudioToPlayUseCase

internal class SaveAudioToPlayUseCaseImpl(private val settings: Settings) : SaveAudioToPlayUseCase {
    override suspend fun invoke(input: String) {
        settings.setAudioPath(input)
    }
}