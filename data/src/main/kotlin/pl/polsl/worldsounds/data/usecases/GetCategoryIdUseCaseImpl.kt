package pl.polsl.worldsounds.data.usecases

import pl.polsl.worldsounds.data.settings.Settings
import pl.polsl.worldsounds.domain.usecases.GetCategoryIdUseCase

internal class GetCategoryIdUseCaseImpl(private val settings: Settings) : GetCategoryIdUseCase {
    override suspend fun invoke(input: Unit): Long {
        return settings.getCategoryId()
    }
}