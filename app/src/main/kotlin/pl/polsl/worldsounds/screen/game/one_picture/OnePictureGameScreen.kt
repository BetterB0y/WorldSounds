package pl.polsl.worldsounds.screen.game.one_picture

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
import pl.polsl.worldsounds.ui.components.buttons.SelectedButton
import pl.polsl.worldsounds.ui.resources.D
import java.io.File

@Destination
@Composable
fun OnePictureGameScreen(
    viewModel: OnePictureGameViewModel = hiltViewModel(),
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


    if (state !is OnePictureGameScreenState.InitialState) {
        OnePictureGameScreen(
            state = state,
            context = context,
            playAudio = viewModel::playAudio,
            saveAudio = viewModel::saveAudio,
            navigateToMainScreen = viewModel::navigateToMainScreen,
            processAnswer = viewModel::processAnswer,
        )
    }
}

@Composable
private fun OnePictureGameScreen(
    context: Context,
    state: OnePictureGameScreenState,
    playAudio: (Context, File) -> Unit,
    saveAudio: (File) -> Unit,
    navigateToMainScreen: () -> Unit,
    processAnswer: (String, Boolean, (Boolean) -> Unit) -> Unit,
) {
    val assets = state.currentRoundData
    var selectedAudioName by remember { mutableStateOf("") }

    GameScreen(
        state = state,
        selectedName = selectedAudioName,
        resetSelectedName = { selectedAudioName = "" },
        navigateToMainScreen = navigateToMainScreen,
        processAnswer = processAnswer,
    ) {
        ImageCard(modifier = Modifier.padding(10.dp), file = assets.image.file) {}
        LazyRow(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items(
                items = assets.audios.filter { !it.isHidden },
                key = { it.file }) {
                SelectedButton(
                    text = "Play",
                    isSelected = selectedAudioName == it.file.nameWithoutExtension,
                    modifier = Modifier.padding(D.Padding.paddingSmall)
                ) {
                    playAudio(context, it.file)
                    saveAudio(it.file)
                    selectedAudioName = it.file.nameWithoutExtension
                }
            }
        }
    }
}