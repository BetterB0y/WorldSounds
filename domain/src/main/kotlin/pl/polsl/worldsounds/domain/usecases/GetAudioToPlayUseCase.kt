package pl.polsl.worldsounds.domain.usecases

import pl.polsl.worldsounds.domain.base.SuspendUseCase

interface GetAudioToPlayUseCase : SuspendUseCase<Unit, String>