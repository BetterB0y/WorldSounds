package pl.polsl.worldsounds.screen.game_mode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.polsl.worldsounds.base.Event
import pl.polsl.worldsounds.base.observeEvents
import pl.polsl.worldsounds.data.models.GameModeUi
import pl.polsl.worldsounds.ui.components.MainButton

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
        saveAndNavigate = viewModel::saveAndNavigate
    )
}

@Composable
private fun GameModeScreen(
    saveAndNavigate: (GameModeUi) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        MainButton(text = "Nutki", onClick = { saveAndNavigate(GameModeUi.ImageToSound) })
        MainButton(text = "Obrazki", onClick = { saveAndNavigate(GameModeUi.SoundToImage) })
    }
}