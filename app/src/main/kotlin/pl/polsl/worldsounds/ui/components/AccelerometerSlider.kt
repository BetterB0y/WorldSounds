package pl.polsl.worldsounds.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pl.polsl.worldsounds.R
import pl.polsl.worldsounds.domain.base.Config
import pl.polsl.worldsounds.ui.resources.D
import java.util.Locale

@Composable
fun AccelerometerSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: () -> Unit,
) {
    CustomSlider(
        value = value,
        text = R.string.accelerometerSensitivity,
        textArg = String.format(Locale.US, "%.2f", value),
        onValueChange = onValueChange,
        valueRange = Config.AccelerometerParameters.minThreshold..Config.AccelerometerParameters.maxThreshold,
        steps = 0,
        onValueChangeFinished = onValueChangeFinished,
        modifier = Modifier.padding(horizontal = D.Padding.paddingMedium),
    )
}