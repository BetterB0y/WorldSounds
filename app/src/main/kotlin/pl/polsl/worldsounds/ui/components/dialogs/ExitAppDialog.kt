package pl.polsl.worldsounds.ui.components.dialogs

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import pl.polsl.worldsounds.R
import pl.polsl.worldsounds.ui.components.buttons.DialogButtonsRow

@Composable
fun ExitAppDialog(
    onDismiss: () -> Unit,
) {
    val context = LocalContext.current

    BaseDialog(title = R.string.exitApp, description = R.string.exitAppAreYouSure, onDismiss = { }) {
        DialogButtonsRow(
            onConfirm = { (context as? Activity)?.finish() },
            onDismiss = onDismiss
        )
    }
}