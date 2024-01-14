package pl.polsl.worldsounds.ui.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import pl.polsl.worldsounds.ui.components.buttons.base.FilledButton
import pl.polsl.worldsounds.ui.resources.D


@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    elevation: Dp = D.Elevation.default,
    onClick: (() -> Unit)?,
) {
    FilledButton(
        modifier = modifier,
        text = text,
        color = MaterialTheme.colorScheme.primary,
        elevation = elevation,
        onClick = onClick
    )
}

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    elevation: Dp = D.Elevation.default,
    @DrawableRes icon: Int,
    iconDescription: String? = null,
    onClick: (() -> Unit)?,
) {
    FilledButton(
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary,
        elevation = elevation,
        onClick = onClick,
        content = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = iconDescription,
            )
        },
    )
}
