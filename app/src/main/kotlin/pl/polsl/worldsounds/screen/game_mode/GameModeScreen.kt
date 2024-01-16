package pl.polsl.worldsounds.screen.game_mode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.polsl.worldsounds.base.Event
import pl.polsl.worldsounds.base.observeEvents
import pl.polsl.worldsounds.models.GameModeData
import pl.polsl.worldsounds.ui.components.buttons.GlobalGoBackButton
import pl.polsl.worldsounds.ui.components.buttons.RoundPrimaryButton
import pl.polsl.worldsounds.ui.resources.D

@Destination
@Composable
fun GameModeScreen(
    viewModel: GameModeViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    viewModel.events.observeEvents {
        when (it) {
            is Event.Navigation -> it.navigate(navigator)
        }
    }

    GameModeScreen(
        saveAndNavigate = viewModel::saveAndNavigate,
        navigateBack = viewModel::navigateBack,
    )
}

@Composable
private fun GameModeScreen(
    saveAndNavigate: (GameModeData) -> Unit,
    navigateBack: () -> Unit,
) {
    GlobalGoBackButton(navigateBack)
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RoundPrimaryButton(
            icon = GameModeData.OnePicture.icon,
            iconDescription = GameModeData.OnePicture.iconDescription,
            iconSize = D.Size.playIconSize,
            size = D.Size.playButton,
            modifier = Modifier
                .padding(D.Padding.paddingSmall)
        ) {
            saveAndNavigate(GameModeData.OnePicture)
        }
        RoundPrimaryButton(
            icon = GameModeData.OneSound.icon,
            iconDescription = GameModeData.OneSound.iconDescription,
            iconSize = D.Size.playIconSize,
            size = D.Size.playButton,
            modifier = Modifier
                .padding(D.Padding.paddingSmall)
        ) {
            saveAndNavigate(GameModeData.OneSound)
        }
    }
}