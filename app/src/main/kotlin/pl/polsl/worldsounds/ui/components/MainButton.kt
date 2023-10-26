package pl.polsl.worldsounds.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.polsl.worldsounds.ui.resources.D


@Composable
fun MainButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .fillMaxWidth(0.4f)
            .height(70.dp)
            .padding(D.Padding.paddingSmall),
        onClick = onClick,
    ) {
        Text(text)
    }

}