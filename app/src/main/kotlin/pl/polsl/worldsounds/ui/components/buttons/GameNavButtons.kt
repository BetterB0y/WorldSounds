package pl.polsl.worldsounds.ui.components.buttons

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
    onExit: () -> Unit,
    onNext: (String) -> Unit,
    selectedName: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = D.Padding.paddingDoubleBig),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = { onExit() }) {
            Text("Wyjdź")
        }

        Button(onClick = { onNext(selectedName) }) {
            Text("Zatwierdź")
        }
    }
}