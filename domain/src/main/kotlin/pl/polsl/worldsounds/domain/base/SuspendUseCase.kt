package pl.polsl.worldsounds.domain.base

interface SuspendUseCase<INPUT, OUTPUT> {
    suspend operator fun invoke(input: INPUT): OUTPUT
}
