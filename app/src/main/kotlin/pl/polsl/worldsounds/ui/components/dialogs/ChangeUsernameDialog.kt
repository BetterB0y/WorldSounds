package pl.polsl.worldsounds.ui.components.dialogs

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import pl.polsl.worldsounds.R
import pl.polsl.worldsounds.ui.components.buttons.DialogButtonsRow


@Composable
fun ChangeUsernameDialog(
    username: String,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    var textFieldValue by rememberSaveable(username) {
        mutableStateOf(username)
    }
    BaseDialog(title = R.string.changeUsername, description = R.string.yourNewUsername, onDismiss = { }) {

        OutlinedTextField(
            value = textFieldValue,
            onValueChange = { textFieldValue = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
        )
        DialogButtonsRow(
            onConfirm = { onConfirm(textFieldValue) },
            onDismiss = onDismiss,
        )

    }
}