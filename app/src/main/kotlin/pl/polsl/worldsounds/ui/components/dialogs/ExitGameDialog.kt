package pl.polsl.worldsounds.ui.components.dialogs

import androidx.compose.runtime.Composable
import pl.polsl.worldsounds.R
import pl.polsl.worldsounds.ui.components.buttons.DialogButtonsRow

@Composable
fun ExitGameDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    BaseDialog(title = R.string.exitGame, description = R.string.exitGameAreYouSure, onDismiss = { }) {
        DialogButtonsRow(
            onConfirm = onConfirm,
            onDismiss = onDismiss
        )
    }
}