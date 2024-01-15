package pl.polsl.worldsounds.data.usecases

import pl.polsl.worldsounds.data.settings.Settings
import pl.polsl.worldsounds.domain.usecases.GetLanguageUseCase

internal class GetLanguageUseCaseImpl(private val settings: Settings) : GetLanguageUseCase {
    override suspend fun invoke(input: Unit): String {
        return settings.getLanguage()
    }
}