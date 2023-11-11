package pl.polsl.worldsounds.data.usecases

import pl.polsl.worldsounds.data.settings.Settings
import pl.polsl.worldsounds.domain.usecases.GetAudioToPlayUseCase

internal class GetAudioToPlayUseCaseImpl(private val settings: Settings) : GetAudioToPlayUseCase {
    override suspend fun invoke(input: Unit): String {
        return settings.getAudioPath()
    }
}