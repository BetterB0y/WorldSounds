package pl.polsl.worldsounds.screen.game.one_sound

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.polsl.worldsounds.base.Event
import pl.polsl.worldsounds.base.observeEvents
import pl.polsl.worldsounds.base.observeState
import pl.polsl.worldsounds.screen.game.GameEvent
import pl.polsl.worldsounds.screen.game.GameScreen
import pl.polsl.worldsounds.ui.components.ImageCard
import pl.polsl.worldsounds.ui.components.buttons.AudioButton
import java.io.File

@Destination
@Composable
fun OneSoundGameScreen(
    viewModel: OneSoundGameViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val context = LocalContext.current
    val state by viewModel.observeState()

    viewModel.events.observeEvents {
        when (it) {
            is GameEvent.OpenSummaryScreen -> it.pushReplacement(navigator)
            is Event.Navigation -> it.navigate(navigator)
        }
    }

    if (state !is OneSoundGameScreenState.InitialState) {
        OneSoundGameScreen(
            context = context,
            state = state,
            playAudio = viewModel::playAudio,
            navigateToMainScreen = viewModel::navigateToMainScreen,
            processAnswer = viewModel::processAnswer,
        )
    }
}

@Composable
private fun OneSoundGameScreen(
    context: Context,
    state: OneSoundGameScreenState,
    playAudio: (Context, File) -> Unit,
    navigateToMainScreen: () -> Unit,
    processAnswer: (Context, String, (Boolean) -> Unit) -> Unit,
) {
    val assets = state.currentRoundData
    var selectedImageName by remember { mutableStateOf("") }

    GameScreen(
        state = state,
        selectedName = selectedImageName,
        resetSelectedName = { selectedImageName = "" },
        navigateToMainScreen = navigateToMainScreen,
        processAnswer = processAnswer,
    ) {
        AudioButton {
            playAudio(context, assets.audio.file)
        }
        LazyRow(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items(
                items = assets.images.filter { !it.isHidden },
                key = { it.file }) {
                ImageCard(
                    modifier = Modifier.padding(10.dp),
                    file = it.file,
                    isSelected = selectedImageName == it.file.nameWithoutExtension
                ) {
                    selectedImageName = it.file.nameWithoutExtension
                }
            }
        }
    }
}
