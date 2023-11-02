package pl.polsl.worldsounds.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import pl.polsl.worldsounds.ui.resources.D

@Composable
fun DescriptionButton(
    description: String,
    onClick: () -> Unit,
    buttonText: String,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            description,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = D.Padding.paddingDoubleBig)
        )
        Button(
            onClick = onClick,
        ) {
            Text(buttonText, modifier = Modifier.padding(D.Padding.paddingMedium))
        }
    }
}