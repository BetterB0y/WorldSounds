package pl.polsl.worldsounds.screen.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import pl.polsl.worldsounds.R
import pl.polsl.worldsounds.base.observeState
import pl.polsl.worldsounds.ui.components.RoundsSlider
import pl.polsl.worldsounds.ui.components.dialogs.ChangeUsernameDialog

@Destination
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val state by viewModel.observeState()

    if (state is SettingsScreenState.ReadyState) {
        SettingsScreen(
            state = state,
            changeLanguage = viewModel::changeLanguage,
            changeUsername = viewModel::changeUsername,
            saveNumberOfRounds = viewModel::saveNumberOfRounds,
            onSliderChange = viewModel::onSliderChange
        )
    }
}

@Composable
private fun SettingsScreen(
    state: SettingsScreenState,
    changeLanguage: (String) -> Unit,
    changeUsername: (String) -> Unit,
    saveNumberOfRounds: (Float) -> Unit,
    onSliderChange: (Float) -> Unit
) {
    var isChangeUsernameDialogVisible: Boolean by remember {
        mutableStateOf(false)
    }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = stringResource(R.string.language))
        Row {
            Button(onClick = {
                changeLanguage("pl")
            }) {
                Text("Polski")
            }
            Button(onClick = {
                changeLanguage("en")
            }) {
                Text("English")
            }
        }
        Button(onClick = {
            isChangeUsernameDialogVisible = true
        }) {
            Text("Zmień nazwę użytkownika")
        }
        if (isChangeUsernameDialogVisible) {
            ChangeUsernameDialog(
                username = state.username,
                onConfirm = {
                    changeUsername(it)
                    isChangeUsernameDialogVisible = false
                },
                onDismiss = { isChangeUsernameDialogVisible = false },
            )
        }
        RoundsSlider(
            value = state.numberOfRounds,
            onValueChange = onSliderChange,
            onValueChangeFinished = { saveNumberOfRounds(state.numberOfRounds) }
        )

    }
}
