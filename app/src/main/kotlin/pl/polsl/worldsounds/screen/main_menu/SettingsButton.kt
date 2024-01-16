package pl.polsl.worldsounds.screen.main_menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import pl.polsl.worldsounds.R
import pl.polsl.worldsounds.ui.components.buttons.RoundSecondaryButton
import pl.polsl.worldsounds.ui.resources.D


@Composable
fun SettingsButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(D.Padding.paddingDoubleBig),
        contentAlignment = Alignment.TopStart
    ) {
        RoundSecondaryButton(
            icon = R.drawable.settings,
            iconDescription = R.string.iconSettings,
            onClick = onClick,
            size = D.Size.settingsButton,
        )
    }
}