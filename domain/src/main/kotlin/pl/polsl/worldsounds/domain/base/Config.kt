package pl.polsl.worldsounds.domain.base

object Config {
    const val appFolder = "WorldSounds/"

    object GameParameters {
        const val maxNumbersOfRounds = 10f
        const val minNumbersOfRounds = 1f
        const val sliderSteps = (maxNumbersOfRounds - minNumbersOfRounds - 1).toInt()
    }

    object AccelerometerParameters {
        const val minThreshold = 0f
        const val maxThreshold = 20f
    }
}
