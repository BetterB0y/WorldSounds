package pl.polsl.worldsounds.domain.base

object Config {
    const val basePath = "/storage/emulated/0/WorldSounds/"

    object GameParameters {
        const val maxNumbersOfRounds = 10f
        const val minNumbersOfRounds = 1f
        const val sliderSteps = (maxNumbersOfRounds - minNumbersOfRounds - 1).toInt()
    }
}