package pl.polsl.worldsounds.data.usecases

import pl.polsl.worldsounds.data.settings.Settings
import pl.polsl.worldsounds.domain.usecases.SaveAccelerometerThresholdUseCase

internal class SaveAccelerometerThresholdUseCaseImpl(private val settings: Settings) :
    SaveAccelerometerThresholdUseCase {
    override suspend fun invoke(input: Float) {
        settings.setAccelerometerThreshold(input)
    }
}