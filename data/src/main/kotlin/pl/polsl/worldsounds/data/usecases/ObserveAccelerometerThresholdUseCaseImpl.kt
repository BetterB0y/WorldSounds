package pl.polsl.worldsounds.data.usecases

import kotlinx.coroutines.flow.Flow
import pl.polsl.worldsounds.data.settings.Settings
import pl.polsl.worldsounds.domain.usecases.ObserveAccelerometerThresholdUseCase

internal class ObserveAccelerometerThresholdUseCaseImpl(private val settings: Settings) :
    ObserveAccelerometerThresholdUseCase {
    override fun invoke(input: Unit): Flow<Float> {
        return settings.observeAccelerometerThreshold()
    }
}