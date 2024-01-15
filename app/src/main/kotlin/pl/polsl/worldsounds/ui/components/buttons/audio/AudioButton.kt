package pl.polsl.worldsounds.ui.components.buttons.audio

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.polsl.worldsounds.R
import pl.polsl.worldsounds.ui.components.buttons.base.FilledButton
import pl.polsl.worldsounds.ui.resources.S

@Composable
fun AudioButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    FilledButton(
        modifier = modifier
            .border(
                shape = S.large,
                border = BorderStroke(
                    width = 0.dp,
                    color = Color.Transparent
                )
            ),
        color = Color.White,
        elevation = 5.dp,
        onClick = onClick,
        content = {
            Icon(
                painter = painterResource(id = R.drawable.audio),
                tint = MaterialTheme.colorScheme.tertiary,
                contentDescription = stringResource(R.string.audioButton),
            )
        },
    )
}