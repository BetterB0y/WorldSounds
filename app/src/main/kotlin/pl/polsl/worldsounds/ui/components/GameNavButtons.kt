package pl.polsl.worldsounds.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pl.polsl.worldsounds.ui.resources.D

@Composable
fun GameNavButtons(
    navigateToMainScreen: () -> Unit,
    processAnswer: (String) -> Unit,
    selectedName: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = D.Padding.paddingDoubleBig),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = { navigateToMainScreen() }) {
            Text("Wyjdź")
        }

        Button(onClick = { processAnswer(selectedName) }) {
            Text("Zatwierdź")
        }
    }
}