package pl.polsl.worldsounds.ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pl.polsl.worldsounds.models.Language


@Composable
fun LanguageButton(
    modifier: Modifier = Modifier,
    language: Language,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .width(70.dp)
            .border(
                if (isSelected) BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
                else BorderStroke(0.dp, Color.Transparent)
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = language.icon),
            contentDescription = language.iconDescription,
        )
    }

}
