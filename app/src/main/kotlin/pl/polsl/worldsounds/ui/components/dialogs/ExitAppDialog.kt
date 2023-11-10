package pl.polsl.worldsounds.ui.components.dialogs

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import pl.polsl.worldsounds.R

@Composable
fun ExitAppDialog(
    onDismiss: () -> Unit,
) {
    val context = LocalContext.current

    BaseDialog(title = "Wyjdź z aplikacji", description = "Czy na pewno chcesz wyjść z aplikacji?", onDismiss = { }) {
        DialogButtonsRow(
            actionText = R.string.exit,
            onConfirm = { (context as? Activity)?.finish() },
            onDismiss = onDismiss
        )
    }
}