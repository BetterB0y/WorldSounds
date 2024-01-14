package pl.polsl.worldsounds.ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pl.polsl.worldsounds.ui.components.buttons.base.FilledButton
import pl.polsl.worldsounds.ui.resources.D
import pl.polsl.worldsounds.ui.resources.S


@Composable
fun SelectedButton(
    text: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit,
) {
    FilledButton(
        text = text,
        color = MaterialTheme.colorScheme.primary,
        elevation = D.Elevation.default,
        modifier = modifier
            .border(
                shape = S.large,
                border = if (isSelected) BorderStroke(width = 2.dp, color = Color.Red) else BorderStroke(
                    width = 0.dp,
                    color = Color.Transparent
                )
            )
    ) {
        onClick()
    }
}