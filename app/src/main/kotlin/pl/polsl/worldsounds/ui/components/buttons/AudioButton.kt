package pl.polsl.worldsounds.ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
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
    isSelected: Boolean = false,
    onClick: () -> Unit,
) {
    FilledButton(
        modifier = modifier
            .size(130.dp)
            .aspectRatio(1f, true)
            .border(
                shape = S.large,
                border = if (isSelected) BorderStroke(
                    width = 4.dp,
                    color = MaterialTheme.colorScheme.tertiary
                ) else BorderStroke(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.secondary
                )
            ),
        color = Color.White,
        elevation = 5.dp,
        onClick = onClick,
        content = {
            Icon(
                painter = painterResource(id = R.drawable.audio),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = stringResource(R.string.audioButton),
                modifier = Modifier.size(60.dp),
            )
        },
    )
}