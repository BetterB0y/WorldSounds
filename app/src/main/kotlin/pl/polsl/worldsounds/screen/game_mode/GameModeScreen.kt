package pl.polsl.worldsounds.screen.game_mode

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.polsl.worldsounds.base.Event
import pl.polsl.worldsounds.base.observeEvents
import pl.polsl.worldsounds.models.GameModeData
import pl.polsl.worldsounds.ui.components.ImageCard
import pl.polsl.worldsounds.ui.components.MainButton
import pl.polsl.worldsounds.utils.FileHelper

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
        playAudio = viewModel::playAudio,
    )
}

@Composable
private fun GameModeScreen(
    saveAndNavigate: (GameModeData) -> Unit,
    playAudio: (Context, String) -> Unit,
) {

    var isImageSelected by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        MainButton(text = "Nutki", onClick = { saveAndNavigate(GameModeData.OnePicture) })
        MainButton(text = "Obrazki", onClick = { saveAndNavigate(GameModeData.OneSound) })
        ImageCard(
            FileHelper.buildFile("Animals", "1.jpg"),
            onClick = {
                playAudio(context, "Animals/1.mp3")
                isImageSelected = !isImageSelected
            },
            isSelected = isImageSelected

        )
    }
}