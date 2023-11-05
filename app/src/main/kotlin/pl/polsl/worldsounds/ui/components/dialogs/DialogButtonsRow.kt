package pl.polsl.worldsounds.ui.components.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pl.polsl.worldsounds.ui.resources.D

@Composable
fun DialogButtonsRow(onDismiss: (() -> Unit), onConfirm: (() -> Unit)) {
    Row(
        modifier = Modifier.padding(top = D.Padding.Dialog.buttonsSpacer),
    ) {
        Button(
            modifier = Modifier.weight(1f),
            onClick = onDismiss
        ) {
            Text("Anuluj")
        }
        Box(Modifier.width(D.Padding.Dialog.buttons))
        Button(
            modifier = Modifier.weight(1f),
            onClick = onConfirm
        ) {
            Text("Zapisz")
        }
    }
}