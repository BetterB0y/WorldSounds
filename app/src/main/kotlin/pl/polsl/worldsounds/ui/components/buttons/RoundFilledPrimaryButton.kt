package pl.polsl.worldsounds.ui.components.buttons

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import pl.polsl.worldsounds.ui.components.buttons.base.RoundFilledButton
import pl.polsl.worldsounds.ui.resources.D


@Composable
fun RoundFilledPrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    elevation: Dp = D.Elevation.default,
    onClick: (() -> Unit)?,
) {
    RoundFilledButton(
        modifier = modifier,
        text = text,
        color = MaterialTheme.colorScheme.primary,
        elevation = elevation,
        onClick = onClick
    )
}

@Composable
fun RoundFilledPrimaryButton(
    modifier: Modifier = Modifier,
    elevation: Dp = D.Elevation.default,
    @DrawableRes icon: Int,
    @StringRes iconDescription: Int? = null,
    onClick: (() -> Unit)?,
) {
    RoundFilledButton(
        modifier = modifier
            .height(D.Size.Height.playButton)
            .width(D.Size.Width.playButton)
            .graphicsLayer(alpha = 0.7f),
        color = MaterialTheme.colorScheme.primary,
        elevation = elevation,
        onClick = onClick,
        content = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = if (iconDescription != null) stringResource(iconDescription) else null,
            )
        },
    )
}
