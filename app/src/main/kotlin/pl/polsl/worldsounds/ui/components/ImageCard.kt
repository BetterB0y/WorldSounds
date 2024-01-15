package pl.polsl.worldsounds.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pl.polsl.worldsounds.R
import java.io.File


@Composable
fun ImageCard(
    file: File,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    isActive: Boolean = true,
    onClick: () -> Unit,
) {
    AsyncImage(
        model = file,
        contentDescription = stringResource(R.string.imageDescription),
        modifier = modifier
            .size(150.dp)
            .aspectRatio(1f)
            .border(
                border =
                if (isSelected) BorderStroke(width = 2.dp, color = Color.Red) else BorderStroke(
                    width = 0.dp,
                    color = Color.Transparent
                )
            )
            .then(if (isActive) Modifier.clickable(onClick = onClick) else Modifier),
        contentScale = ContentScale.Crop,
        colorFilter = if (!isActive) ColorFilter.colorMatrix(ColorMatrix().apply {
            setToSaturation(0.0f)
        }) else null
    )
}