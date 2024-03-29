package pl.polsl.worldsounds.screen.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.polsl.worldsounds.R
import pl.polsl.worldsounds.base.Event
import pl.polsl.worldsounds.base.observeEvents
import pl.polsl.worldsounds.base.observeState
import pl.polsl.worldsounds.models.Language
import pl.polsl.worldsounds.ui.components.AccelerometerSlider
import pl.polsl.worldsounds.ui.components.RoundsSlider
import pl.polsl.worldsounds.ui.components.buttons.GlobalGoBackButton
import pl.polsl.worldsounds.ui.components.buttons.LanguageButton
import pl.polsl.worldsounds.ui.components.buttons.PrimaryButton
import pl.polsl.worldsounds.ui.components.dialogs.AuthorsDialog
import pl.polsl.worldsounds.ui.components.dialogs.ChangeUsernameDialog
import pl.polsl.worldsounds.ui.resources.D
import pl.polsl.worldsounds.ui.resources.S

@Destination
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val state by viewModel.observeState()

    viewModel.events.observeEvents {
        when (it) {
            is Event.Navigation -> it.navigate(navigator)
        }
    }

    if (state is SettingsScreenState.ReadyState) {
        SettingsScreen(
            state = state,
            changeLanguage = viewModel::changeLanguage,
            changeUsername = viewModel::changeUsername,
            saveNumberOfRounds = viewModel::saveNumberOfRounds,
            onRoundSliderChange = viewModel::onRoundSliderChange,
            saveAccelerometerThreshold = viewModel::saveAccelerometerThreshold,
            onAccelerometerSliderChange = viewModel::onAccelerometerSliderChange,
            navigateBack = viewModel::navigateBack,
        )
    }
}

@Composable
private fun SettingsScreen(
    state: SettingsScreenState,
    changeLanguage: (Language) -> Unit,
    changeUsername: (String) -> Unit,
    saveNumberOfRounds: () -> Unit,
    onRoundSliderChange: (Float) -> Unit,
    saveAccelerometerThreshold: () -> Unit,
    onAccelerometerSliderChange: (Float) -> Unit,
    navigateBack: () -> Unit,
) {
    var isChangeUsernameDialogVisible: Boolean by remember {
        mutableStateOf(false)
    }

    var isAuthorsInfoDialogVisible: Boolean by remember {
        mutableStateOf(false)
    }

    val scrollState = rememberScrollState()

    GlobalGoBackButton(navigateBack)
    AuthorButton {
        isAuthorsInfoDialogVisible = true
    }
    if (isAuthorsInfoDialogVisible) {
        AuthorsDialog(
            onDismiss = { isAuthorsInfoDialogVisible = false },
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier
                .fillMaxWidth(0.45f)
                .fillMaxHeight(0.9f)
                .verticalScroll(scrollState)
                .clip(S.large)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = stringResource(R.string.language), modifier = Modifier.padding(D.Padding.paddingSmall))
            Row {
                LanguageButton(
                    language = Language.POLISH,
                    isSelected = state.language == Language.POLISH,
                    modifier = Modifier.padding(D.Padding.paddingSmall),
                ) {
                    changeLanguage(Language.POLISH)
                }
                LanguageButton(
                    language = Language.ENGLISH,
                    isSelected = state.language == Language.ENGLISH,
                    modifier = Modifier.padding(D.Padding.paddingSmall)
                ) {
                    changeLanguage(Language.ENGLISH)
                }
            }
            PrimaryButton(
                text = stringResource(R.string.changeUsername),
                modifier = Modifier.padding(D.Padding.paddingSmall)
            ) {
                isChangeUsernameDialogVisible = true
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
                onValueChange = onRoundSliderChange,
                onValueChangeFinished = saveNumberOfRounds
            )
            AccelerometerSlider(
                value = state.accelerometerThreshold,
                onValueChange = onAccelerometerSliderChange,
                onValueChangeFinished = saveAccelerometerThreshold
            )
        }
    }
}
