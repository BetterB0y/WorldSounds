package pl.polsl.worldsounds.ui.components.dialogs

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import pl.polsl.worldsounds.ui.components.buttons.base.EmptyButton
import pl.polsl.worldsounds.ui.components.buttons.base.FilledButton
import pl.polsl.worldsounds.ui.resources.D

@Composable
fun DialogButtonsRow(
    @StringRes actionText: Int,
    onDismiss: (() -> Unit),
    onConfirm: (() -> Unit)
) {
    Row(
        modifier = Modifier.padding(top = D.Padding.Dialog.buttonsSpacer),
    ) {
        EmptyButton(
            text = "Anuluj",
            modifier = Modifier.weight(1f),
        ) {
            onDismiss()
        }
        Box(Modifier.width(D.Padding.Dialog.buttons))
        FilledButton(
            text = stringResource(actionText),
            modifier = Modifier.weight(1f),
        ) {
            onConfirm()
        }
    }
}