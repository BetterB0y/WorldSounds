package pl.polsl.worldsounds.ui.components.dialogs

import androidx.compose.runtime.Composable
import pl.polsl.worldsounds.R

@Composable
fun ExitGameDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    BaseDialog(title = "Wyjdź z gry", description = "Czy na pewno chcesz wyjść z gry?", onDismiss = { }) {
        DialogButtonsRow(
            actionText = R.string.exit,
            onConfirm = onConfirm,
            onDismiss = onDismiss
        )
    }
}