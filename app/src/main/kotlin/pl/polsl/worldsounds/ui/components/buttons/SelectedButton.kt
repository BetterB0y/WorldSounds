package pl.polsl.worldsounds.ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun SelectedButton(
    text: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .border(
                border =
                if (isSelected) BorderStroke(width = 2.dp, color = Color.Red) else BorderStroke(
                    width = 0.dp,
                    color = Color.Transparent
                )
            ),
        onClick = onClick
    ) {
        Text(text)
    }
}