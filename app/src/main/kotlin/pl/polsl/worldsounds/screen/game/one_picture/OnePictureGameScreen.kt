package pl.polsl.worldsounds.screen.game.one_picture

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
    processAnswer: (Context, String, (Boolean) -> Unit) -> Unit,
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageCard(file = assets.image.file)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.width(350.dp)
            ) {
                items(
                    items = assets.audios.filter { !it.isHidden },
                    key = { it.file }
                ) { audio ->
                    AudioButton(
                        isSelected = selectedAudioName == audio.file.nameWithoutExtension,
                        modifier = Modifier.padding(2.dp),
                    ) {
                        playAudio(context, audio.file)
                        saveAudio(audio.file)
                        selectedAudioName = audio.file.nameWithoutExtension
                    }
                }
            }
        }
    }
}