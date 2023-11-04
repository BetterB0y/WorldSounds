package pl.polsl.worldsounds.screen.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.polsl.worldsounds.base.Event
import pl.polsl.worldsounds.base.observeEvents
import pl.polsl.worldsounds.base.observeState
import pl.polsl.worldsounds.models.GameModeData

@Destination
@Composable
fun GameScreen(
    viewModel: GameViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val state by viewModel.observeState()

    viewModel.events.observeEvents {
        when (it) {
            is GameEvent.OpenMainMenuScreen -> it.pushReplacement(navigator)
            is Event.Navigation -> it.navigate(navigator)
        }
    }

    if (state != GameScreenState.InitialState) {
        if (state.gameMode == GameModeData.OnePicture) {
            OnePictureGameScreen(state, viewModel::playAudio)
        } else {
            OneSoundGameScreen(
                state,
                viewModel::playAudio,
                viewModel::navigateToMainScreen
            )
        }
    }
}
