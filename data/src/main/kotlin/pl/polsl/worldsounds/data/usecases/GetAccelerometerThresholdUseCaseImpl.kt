package pl.polsl.worldsounds.data.usecases

import pl.polsl.worldsounds.data.settings.Settings
import pl.polsl.worldsounds.domain.usecases.GetAccelerometerThresholdUseCase

internal class GetAccelerometerThresholdUseCaseImpl(private val settings: Settings) : GetAccelerometerThresholdUseCase {
    override suspend fun invoke(input: Unit): Float {
        return settings.getAccelerometerThreshold()
    }
}
