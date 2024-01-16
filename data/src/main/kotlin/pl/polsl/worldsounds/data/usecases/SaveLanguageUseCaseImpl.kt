package pl.polsl.worldsounds.data.usecases

import pl.polsl.worldsounds.data.settings.Settings
import pl.polsl.worldsounds.domain.usecases.SaveLanguageUseCase

internal class SaveLanguageUseCaseImpl(private val settings: Settings) : SaveLanguageUseCase {
    override suspend fun invoke(input: String) {
        settings.setLanguage(input)
    }
}