package pl.polsl.worldsounds.ui.components.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import pl.polsl.worldsounds.R
import pl.polsl.worldsounds.ui.components.buttons.PrimaryButton
import pl.polsl.worldsounds.ui.resources.D

@Composable
fun AuthorsDialog(
    onDismiss: () -> Unit,
) {
    BaseDialog(onDismiss = { }) {
        Text(
            stringResource(R.string.implementedFor),
            modifier = Modifier.padding(D.Padding.Dialog.description),
            style = MaterialTheme.typography.bodyMedium,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.authors),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
            )
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(0.5f)
            ) {
                Text(
                    text = "Aleksandra Tlałka",
                    modifier = Modifier.padding(D.Padding.Dialog.description),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "tlalka.ola@gmail.com",
                    modifier = Modifier.padding(D.Padding.Dialog.description),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(0.5f)

            ) {
                Text(
                    text = "Daniel Pakosz",
                    modifier = Modifier.padding(D.Padding.Dialog.description),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "daniel.pakosz@interia.pl",
                    modifier = Modifier.padding(D.Padding.Dialog.description),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                )
            }
        }

        PrimaryButton(
            icon = R.drawable.cross,
            iconDescription = R.string.iconCancel,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = D.Padding.Dialog.buttonsSpacer),
        ) {
            onDismiss()
        }
    }
}