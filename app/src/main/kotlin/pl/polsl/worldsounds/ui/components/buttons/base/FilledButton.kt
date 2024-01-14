package pl.polsl.worldsounds.ui.components.buttons.base

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import pl.polsl.worldsounds.ui.resources.D
import pl.polsl.worldsounds.ui.resources.S

@Composable
fun FilledButton(
    modifier: Modifier,
    text: String,
    color: Color,
    elevation: Dp,
    onClick: (() -> Unit)?,
) {
    FilledButton(
        onClick = onClick,
        elevation = elevation,
        color = color,
        modifier = modifier
    ) {
        Text(
            text = text,
        )
    }

}


@Composable
fun FilledButton(
    modifier: Modifier = Modifier,
    color: Color,
    elevation: Dp = D.Elevation.default,
    onClick: (() -> Unit)?,
    content: @Composable RowScope.() -> Unit,
) {
    Surface(
        shape = S.large,
        shadowElevation = elevation,
        modifier = modifier
            .height(D.Size.Height.button),
    ) {
        Button(
            onClick = onClick ?: {},
            enabled = onClick != null,
            shape = S.large,
            colors = ButtonDefaults.textButtonColors(
                contentColor = Color.White,
                containerColor = color,
                disabledContentColor = Color.White.copy(alpha = 0.6f),
                disabledContainerColor = color.copy(alpha = 0.6f),
            ),
        ) {
            content()
        }
    }
}