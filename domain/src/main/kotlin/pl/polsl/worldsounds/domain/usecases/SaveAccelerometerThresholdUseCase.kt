package pl.polsl.worldsounds.domain.usecases

import pl.polsl.worldsounds.domain.base.SuspendUseCase

interface SaveAccelerometerThresholdUseCase : SuspendUseCase<Float, Unit>
