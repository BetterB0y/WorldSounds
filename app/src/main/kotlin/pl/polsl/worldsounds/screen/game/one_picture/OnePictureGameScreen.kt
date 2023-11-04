package pl.polsl.worldsounds.screen.game.one_picture

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.polsl.worldsounds.base.observeEvents
import pl.polsl.worldsounds.base.observeState
import pl.polsl.worldsounds.screen.game.GameEvent
import pl.polsl.worldsounds.ui.components.ImageCard
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
            is GameEvent.OpenMainMenuScreen -> it.pushReplacement(navigator)
        }
    }

    if (state !is OnePictureGameScreenState.InitialState) {
        OnePictureGameScreen(
            state = state,
            context = context,
            playAudio = viewModel::playAudio,
        )
    }
}

@Composable
private fun OnePictureGameScreen(
    context: Context,
    state: OnePictureGameScreenState,
    playAudio: (Context, File) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        ImageCard(modifier = Modifier.padding(10.dp), file = state.roundAssets.image.file) {}
        LazyRow(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items(
                items = state.roundAssets.audios,
                key = { it.file }) {
                Button(onClick = { playAudio(context, it.file) }) {
                    Text("Play")
                }
            }
        }
    }
}