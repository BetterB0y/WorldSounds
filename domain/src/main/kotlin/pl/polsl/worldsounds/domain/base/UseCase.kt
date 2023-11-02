package pl.polsl.worldsounds.domain.base

interface UseCase<INPUT, OUTPUT> {
    operator fun invoke(input: INPUT): OUTPUT
}