package pl.polsl.worldsounds.screen.main_menu

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.polsl.worldsounds.R
import pl.polsl.worldsounds.app.permissions
import pl.polsl.worldsounds.base.Event
import pl.polsl.worldsounds.base.observeEvents
import pl.polsl.worldsounds.base.observeState
import pl.polsl.worldsounds.ui.components.MultiplePermissionPage
import pl.polsl.worldsounds.ui.components.SnackbarScreenWrapper
import pl.polsl.worldsounds.ui.components.buttons.base.FilledButton
import pl.polsl.worldsounds.ui.components.dialogs.ExitAppDialog
import pl.polsl.worldsounds.ui.components.dialogs.MathRiddleDialog
import pl.polsl.worldsounds.ui.resources.D


@RootNavGraph(start = true)
@Destination
@Composable
fun MainMenuScreen(
    viewModel: MainMenuViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val state by viewModel.observeState()

    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    viewModel.events.observeEvents {
        when (it) {
            is Event.Navigation -> it.navigate(navigator)
            is Event.Message -> snackbarHostState.showSnackbar(it.text(context))
        }
    }
    SnackbarScreenWrapper(snackbarHostState = snackbarHostState) {
        MainMenuScreen(
            state = state,
            navigateToGameModeScreen = viewModel::navigateToGameModeScreen,
            generateRiddle = viewModel::generateRiddle,
            processRiddleAnswer = viewModel::processRiddleAnswer,
            navigateToBestScoresScreen = viewModel::navigateToBestScoresScreen
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun MainMenuScreen(
    state: MainMenuScreenState,
    navigateToGameModeScreen: () -> Unit,
    generateRiddle: () -> Unit,
    processRiddleAnswer: (String) -> Unit,
    navigateToBestScoresScreen: () -> Unit
) {
    val storagePermissionState = rememberMultiplePermissionsState(
        permissions = permissions
    )

    var isExitDialogVisible: Boolean by remember {
        mutableStateOf(false)
    }
    var isMathRiddleDialogVisible: Boolean by remember {
        mutableStateOf(false)
    }

    BackHandler {
        isExitDialogVisible = true
    }

    if (isExitDialogVisible) {
        ExitAppDialog(onDismiss = { isExitDialogVisible = false })
    }
    if (isMathRiddleDialogVisible) {
        MathRiddleDialog(
            riddle = state.riddleData,
            onConfirm = {
                isMathRiddleDialogVisible = false
                processRiddleAnswer(it)
            },
            onDismiss = { isMathRiddleDialogVisible = false })
    }


    MultiplePermissionPage(
        state = storagePermissionState,
        permission = R.string.storagePermission,
        rationale = R.string.storageRationale,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            FilledButton(
                text = "Graj",
                onClick = navigateToGameModeScreen,
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .padding(D.Padding.paddingSmall)
            )
            FilledButton(
                text = "Ustawienia",
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .padding(D.Padding.paddingSmall)
            ) {
                generateRiddle()
                isMathRiddleDialogVisible = true
            }
            FilledButton(
                text = "Zobacz najlepsze wyniki",
                onClick = navigateToBestScoresScreen,
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .padding(D.Padding.paddingSmall),
            )
        }
    }
}
