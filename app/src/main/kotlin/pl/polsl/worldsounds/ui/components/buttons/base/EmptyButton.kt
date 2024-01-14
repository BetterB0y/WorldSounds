package pl.polsl.worldsounds.ui.components.buttons.base

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import pl.polsl.worldsounds.ui.resources.D
import pl.polsl.worldsounds.ui.resources.S

//TODO delete if not used
@Composable
fun EmptyButton(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MaterialTheme.colorScheme.primary,
    onClick: (() -> Unit)?,
) {

    EmptyButton(color = color, onClick = onClick, modifier = modifier) {
        Text(text = text)
    }
}

@Composable
fun EmptyButton(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    onClick: (() -> Unit)?,
    content: @Composable RowScope.() -> Unit,
) {
    Surface(
        border = BorderStroke(
            D.Size.Thickness.borderStroke,
            color
        ),
        shape = S.large,
        shadowElevation = D.Elevation.default,
        modifier = modifier
            .height(D.Size.Height.button),
    ) {
        OutlinedButton(
            onClick = onClick ?: {},
            enabled = onClick != null,
            shape = S.large,
            colors = ButtonDefaults.textButtonColors(
                contentColor = color,
                containerColor = Color.White
            ),
        ) {
            content()
        }
    }
}
