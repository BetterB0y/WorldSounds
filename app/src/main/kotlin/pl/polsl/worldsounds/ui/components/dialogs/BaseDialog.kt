package pl.polsl.worldsounds.ui.components.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import pl.polsl.worldsounds.ui.components.cards.CardTheme
import pl.polsl.worldsounds.ui.resources.D
import pl.polsl.worldsounds.ui.resources.S

@Composable
fun BaseDialog(
    title: String,
    description: String,
    onDismiss: () -> Unit,
    dialogProperties: DialogProperties = DialogProperties(
        usePlatformDefaultWidth = false
    ),
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = dialogProperties,
    ) {

        CardTheme(
            modifier = Modifier.padding(D.Padding.Dialog.fromScreenEdges),
            shape = S.dialog,
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(D.Padding.Dialog.content),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                )
                Text(
                    text = description,
                    modifier = Modifier.padding(D.Padding.Dialog.description),
                    textAlign = TextAlign.Center
                )
                content()
            }
        }
    }
}