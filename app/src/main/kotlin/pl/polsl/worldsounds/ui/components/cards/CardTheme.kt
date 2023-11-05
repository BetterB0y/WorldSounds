package pl.polsl.worldsounds.ui.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import pl.polsl.worldsounds.ui.resources.D
import pl.polsl.worldsounds.ui.resources.S

@Composable
fun CardTheme(
    modifier: Modifier = Modifier,
    elevation: Dp = D.Elevation.default,
    color: Color = Color.White,
    shape: Shape = S.large,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    Surface(
        shape = shape,
        shadowElevation = elevation,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        var boxModifier = Modifier
            .background(color)
            .clip(shape)
        if (onClick != null) {
            boxModifier = boxModifier.clickable(onClick = onClick)
        }
        Box(modifier = boxModifier) {
            content()
        }
    }
}
