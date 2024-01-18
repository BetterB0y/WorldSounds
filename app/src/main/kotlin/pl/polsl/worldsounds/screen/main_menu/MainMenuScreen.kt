package pl.polsl.worldsounds.screen.main_menu

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.polsl.worldsounds.R
import pl.polsl.worldsounds.app.permissions
import pl.polsl.worldsounds.base.Event
import pl.polsl.worldsounds.base.observeEvents
import pl.polsl.worldsounds.ui.components.MultiplePermissionPage
import pl.polsl.worldsounds.ui.components.buttons.RoundPrimaryButton
import pl.polsl.worldsounds.ui.components.buttons.SecondaryButton
import pl.polsl.worldsounds.ui.components.dialogs.ExitAppDialog
import pl.polsl.worldsounds.ui.resources.D


@RootNavGraph(start = true)
@Destination
@Composable
fun MainMenuScreen(
    viewModel: MainMenuViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    viewModel.events.observeEvents {
        when (it) {
            is Event.Navigation -> it.navigate(navigator)
        }
    }

    MainMenuScreen(
        navigateToGameModeScreen = viewModel::navigateToGameModeScreen,
        navigateToBestScoresScreen = viewModel::navigateToBestScoresScreen,
        navigateToSettings = viewModel::navigateToSettings,
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun MainMenuScreen(
    navigateToGameModeScreen: () -> Unit,
    navigateToBestScoresScreen: () -> Unit,
    navigateToSettings: () -> Unit,
) {
    val storagePermissionState = rememberMultiplePermissionsState(
        permissions = permissions
    )

    var isExitDialogVisible: Boolean by remember {
        mutableStateOf(false)
    }

    BackHandler {
        isExitDialogVisible = true
    }

    if (isExitDialogVisible) {
        ExitAppDialog(onDismiss = { isExitDialogVisible = false })
    }

    MultiplePermissionPage(
        state = storagePermissionState,
        permission = R.string.storagePermission,
        rationale = R.string.storageRationale,
    ) {

        SettingsButton(navigateToSettings)
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            Text(
                stringResource(R.string.appName),
                style = MaterialTheme.typography.titleLarge
            )
            RoundPrimaryButton(
                icon = R.drawable.play,
                iconDescription = R.string.iconPlay,
                iconSize = D.Size.playIconSize,
                onClick = navigateToGameModeScreen,
                size = D.Size.playButton,
                modifier = Modifier
                    .padding(D.Padding.paddingSmall)
            )
            SecondaryButton(
                icon = R.drawable.high_scores,
                iconDescription = R.string.iconHighScores,
                onClick = navigateToBestScoresScreen,
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .padding(D.Padding.paddingSmall),
            )
        }
    }
}
