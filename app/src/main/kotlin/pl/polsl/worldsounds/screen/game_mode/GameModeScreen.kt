package pl.polsl.worldsounds.screen.game_mode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import pl.polsl.worldsounds.ui.components.buttons.base.FilledButton
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
    )
}

@Composable
private fun GameModeScreen(
    saveAndNavigate: (GameModeData) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        FilledButton(
            text = "Nutki",
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .padding(D.Padding.paddingSmall),
        ) {
            saveAndNavigate(GameModeData.OnePicture)
        }
        FilledButton(
            text = "Obrazki",
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .padding(D.Padding.paddingSmall),
        ) {
            saveAndNavigate(GameModeData.OneSound)
        }
    }
}