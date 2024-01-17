package pl.polsl.worldsounds.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pl.polsl.worldsounds.R
import pl.polsl.worldsounds.domain.base.Config
import pl.polsl.worldsounds.ui.resources.D

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
        modifier = Modifier.padding(horizontal = D.Padding.paddingMedium),
    )
}