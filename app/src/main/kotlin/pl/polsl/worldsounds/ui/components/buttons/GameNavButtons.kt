package pl.polsl.worldsounds.ui.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pl.polsl.worldsounds.R
import pl.polsl.worldsounds.ui.resources.D

@Composable
fun GameNavButtons(
    onExit: () -> Unit,
    onNext: (String) -> Unit,
    selectedName: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = D.Padding.paddingDoubleBig),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SecondaryButton(
            icon = R.drawable.home,
            iconDescription = "Exit to main menu",
            modifier = Modifier.width(D.Size.Width.button)
        ) {
            onExit()
        }
        PrimaryButton(
            icon = R.drawable.arrow_right,
            iconDescription = "Next",
            modifier = Modifier.width(D.Size.Width.button),
        ) {
            onNext(selectedName)
        }
    }
}