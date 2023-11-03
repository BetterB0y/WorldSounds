package pl.polsl.worldsounds.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pl.polsl.worldsounds.app.Config
import java.io.File


@Composable
fun ImageCard(
    filePath: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    AsyncImage(
        model = File(Config.basePath + filePath),
        contentDescription = "Square Image",
        modifier = Modifier
            .size(150.dp)
            .aspectRatio(1f)
            .border(
                border =
                if (isSelected) BorderStroke(width = 2.dp, color = Color.Black) else BorderStroke(
                    width = 0.dp,
                    color = Color.Transparent
                )
            )
            .clickable {
                onClick()
            },
        contentScale = ContentScale.Crop,
    )
}