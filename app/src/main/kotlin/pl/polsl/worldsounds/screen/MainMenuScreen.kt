package pl.polsl.worldsounds.screen

import android.Manifest
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.polsl.worldsounds.R
import pl.polsl.worldsounds.screen.destinations.GameModeScreenDestination
import pl.polsl.worldsounds.screen.destinations.SettingsScreenDestination
import pl.polsl.worldsounds.ui.components.MainButton
import pl.polsl.worldsounds.ui.components.MultiplePermissionPage


@RootNavGraph(start = true)
@Destination
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainMenuScreen(navigator: DestinationsNavigator) {
    val storagePermissionState = rememberMultiplePermissionsState(
        permissions = buildList {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                add(Manifest.permission.READ_MEDIA_AUDIO)
                add(Manifest.permission.READ_MEDIA_IMAGES)
            } else {
                add(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    )

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
            MainButton(text = "Graj") {
                navigator.navigate(direction = GameModeScreenDestination())
            }
            MainButton(text = "Ustawienia") {
                navigator.navigate(direction = SettingsScreenDestination())
            }
        }

    }
}
