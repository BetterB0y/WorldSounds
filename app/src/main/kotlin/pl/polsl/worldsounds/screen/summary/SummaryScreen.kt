package pl.polsl.worldsounds.screen.summary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.polsl.worldsounds.base.Event
import pl.polsl.worldsounds.base.observeEvents
import pl.polsl.worldsounds.base.observeState

@Destination
@Composable
fun SummaryScreen(
    score: Int,
    viewModel: SummaryViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val state by viewModel.observeState()

    viewModel.events.observeEvents {
        when (it) {
            //TODO fix navigation go back after entering game screen
            is SummaryEvent.OpenOnePictureGame -> it.pushReplacement(navigator)
            is SummaryEvent.OpenOneSoundGame -> it.pushReplacement(navigator)
            is Event.Navigation -> it.navigate(navigator)
        }
    }

    SummaryScreen(
        state = state,
        score = score,
        navigateToMainScreen = viewModel::navigateToMainScreen,
        startGameAgain = viewModel::startGameAgain,
    )
}

@Composable
private fun SummaryScreen(
    state: SummaryScreenState,
    score: Int,
    navigateToMainScreen: () -> Unit,
    startGameAgain: () -> Unit,
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Gratulacje")
        Text("Zdobyłeś $score punktów")
        Button(onClick = startGameAgain) {
            Text("Jeszcze raz")
        }
        Button(onClick = navigateToMainScreen) {
            Text("Wróć")
        }
    }
}