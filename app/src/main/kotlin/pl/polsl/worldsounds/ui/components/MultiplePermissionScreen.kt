package pl.polsl.worldsounds.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionStatus
import pl.polsl.worldsounds.R
import pl.polsl.worldsounds.ui.components.buttons.DescriptionButton
import pl.polsl.worldsounds.ui.resources.D


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MultiplePermissionPage(
    state: MultiplePermissionsState,
    @StringRes permission: Int,
    @StringRes rationale: Int,
    content: @Composable () -> Unit
) {
    if (state.permissions.all { it.status == PermissionStatus.Granted }) {
        content()
    } else {
        LaunchedEffect(
            key1 = "permissionName",
            block = { state.launchMultiplePermissionRequest() }
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier.padding(horizontal = D.Padding.paddingMedium),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val textToShow = if (state.shouldShowRationale) {
                    rationale
                } else {
                    permission
                }
                DescriptionButton(
                    stringResource(textToShow),
                    buttonText = stringResource(R.string.addPermissions),
                    onClick = { state.launchMultiplePermissionRequest() },
                )
            }

        }
    }

}
