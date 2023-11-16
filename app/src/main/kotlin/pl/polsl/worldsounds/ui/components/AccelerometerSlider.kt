package pl.polsl.worldsounds.ui.components

import androidx.compose.runtime.Composable
import pl.polsl.worldsounds.domain.base.Config
import java.util.Locale

@Composable
fun AccelerometerSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: () -> Unit,
) {
    CustomSlider(
        value = value,
        text = "Czułość akcelerometru: ${String.format(Locale.US, "%.2f", value)}",
        onValueChange = onValueChange,
        valueRange = Config.AccelerometerParameters.minThreshold..Config.AccelerometerParameters.maxThreshold,
        steps = 0,
        onValueChangeFinished = onValueChangeFinished,
    )
}