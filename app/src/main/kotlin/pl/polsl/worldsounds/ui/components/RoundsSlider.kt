package pl.polsl.worldsounds.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.polsl.worldsounds.domain.base.Config

@Composable
fun RoundsSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Liczba rund: ${value.toInt()}")
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = Config.GameParameters.minNumbersOfRounds..Config.GameParameters.maxNumbersOfRounds,
            steps = Config.GameParameters.sliderSteps,
            onValueChangeFinished = onValueChangeFinished,
            modifier = Modifier.width(400.dp)
        )
    }

}