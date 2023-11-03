package pl.polsl.worldsounds.data.usecases

import pl.polsl.worldsounds.data.settings.Settings
import pl.polsl.worldsounds.domain.usecases.SaveCategoryIdUseCase

internal class SaveCategoryIdUseCaseImpl(private val settings: Settings) : SaveCategoryIdUseCase {
    override suspend fun invoke(input: Long) {
        settings.setCategoryId(input)
    }
}