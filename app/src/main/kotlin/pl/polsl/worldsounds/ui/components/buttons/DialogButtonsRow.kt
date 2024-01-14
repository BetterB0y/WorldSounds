package pl.polsl.worldsounds.ui.components.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pl.polsl.worldsounds.R
import pl.polsl.worldsounds.ui.resources.D

@Composable
fun DialogButtonsRow(
    onDismiss: (() -> Unit),
    onConfirm: (() -> Unit)
) {
    Row(
        modifier = Modifier.padding(top = D.Padding.Dialog.buttonsSpacer),
    ) {
        SecondaryButton(
            icon = R.drawable.cross,
            iconDescription = "Anuluj",
            modifier = Modifier.weight(1f),
        ) {
            onDismiss()
        }
        Box(Modifier.width(D.Padding.Dialog.buttons))
        PrimaryButton(
            icon = R.drawable.check,
            iconDescription = "Zatwierdź",
            modifier = Modifier.weight(1f),
        ) {
            onConfirm()
        }
    }
}