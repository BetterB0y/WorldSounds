package pl.polsl.worldsounds.screen.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.polsl.worldsounds.R
import pl.polsl.worldsounds.ui.components.buttons.RoundSecondaryButton
import pl.polsl.worldsounds.ui.resources.D


@Composable
fun AuthorButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        RoundSecondaryButton(
            icon = R.drawable.info,
            iconDescription = R.string.iconBack,
            onClick = onClick,
            size = D.Size.goBackButton,
            alpha = 0.9f,
        )
    }
}