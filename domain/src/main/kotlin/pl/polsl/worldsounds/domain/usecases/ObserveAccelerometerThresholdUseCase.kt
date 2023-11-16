package pl.polsl.worldsounds.domain.usecases

import kotlinx.coroutines.flow.Flow
import pl.polsl.worldsounds.domain.base.UseCase

interface ObserveAccelerometerThresholdUseCase : UseCase<Unit, Flow<Float>>