package pl.polsl.worldsounds.ui.components.buttons.audio

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pl.polsl.worldsounds.ui.resources.S

@Composable
fun SelectedAudioButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    AudioButton(
        modifier = modifier
            .border(
                shape = S.large,
                border = if (isSelected) BorderStroke(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.tertiary
                ) else BorderStroke(
                    width = 0.dp,
                    color = Color.Transparent
                )
            ),
        onClick = onClick,
    )
}
