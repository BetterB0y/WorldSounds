package pl.polsl.worldsounds.ui.components

import androidx.compose.runtime.Composable
import pl.polsl.worldsounds.R
import pl.polsl.worldsounds.domain.base.Config

@Composable
fun RoundsSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: () -> Unit,
) {
    CustomSlider(
        value = value,
        text = R.string.roundNumber,
        textArg = value.toInt(),
        onValueChange = onValueChange,
        valueRange = Config.GameParameters.minNumbersOfRounds..Config.GameParameters.maxNumbersOfRounds,
        steps = Config.GameParameters.sliderSteps,
        onValueChangeFinished = onValueChangeFinished,
    )
}