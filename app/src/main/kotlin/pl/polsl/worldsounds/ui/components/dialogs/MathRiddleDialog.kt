package pl.polsl.worldsounds.ui.components.dialogs

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import pl.polsl.worldsounds.models.RiddleData
import pl.polsl.worldsounds.ui.components.buttons.DialogButtonsRow


@Composable
fun MathRiddleDialog(
    riddle: RiddleData,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    var textFieldValue by rememberSaveable(riddle) {
        mutableStateOf("")
    }

    BaseDialog(title = "Rozwiąż zagadkę", description = "Podaj wartość wyrażenia:", onDismiss = { }) {
        Text("${riddle.num1} ${riddle.operator.operator} ${riddle.num2} = ?")
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = { textFieldValue = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                autoCorrect = false,
                imeAction = ImeAction.Done
            ),
        )
        DialogButtonsRow(
            onConfirm = {
                onConfirm(textFieldValue)
                textFieldValue = ""
            },
            onDismiss = onDismiss,
        )
    }
}