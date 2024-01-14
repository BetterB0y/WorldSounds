package pl.polsl.worldsounds.ui.components.dialogs

import androidx.compose.runtime.Composable
import pl.polsl.worldsounds.ui.components.buttons.DialogButtonsRow

@Composable
fun ExitGameDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    BaseDialog(title = "Wyjdź z gry", description = "Czy na pewno chcesz wyjść z gry?", onDismiss = { }) {
        DialogButtonsRow(
            onConfirm = onConfirm,
            onDismiss = onDismiss
        )
    }
}