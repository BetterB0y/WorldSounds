package pl.polsl.worldsounds.screen.summary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.polsl.worldsounds.base.Event
import pl.polsl.worldsounds.base.observeEvents
import pl.polsl.worldsounds.ui.components.buttons.PrimaryButton
import pl.polsl.worldsounds.ui.resources.D

@Destination
@Composable
fun SummaryScreen(
    score: Int,
    viewModel: SummaryViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    viewModel.events.observeEvents {
        when (it) {
            is SummaryEvent.OpenOnePictureGame -> it.pushReplacement(navigator)
            is SummaryEvent.OpenOneSoundGame -> it.pushReplacement(navigator)
            is Event.Navigation -> it.navigate(navigator)
        }
    }

    SummaryScreen(
        score = score,
        navigateToMainScreen = viewModel::navigateToMainScreen,
        startGameAgain = viewModel::startGameAgain,
    )
}

@Composable
private fun SummaryScreen(
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
        PrimaryButton(
            text = "Jeszcze raz",
            onClick = startGameAgain,
            modifier = Modifier
                .padding(D.Padding.paddingSmall)
                .fillMaxWidth(0.4f)
        )
        PrimaryButton(
            text = "Wróć",
            onClick = navigateToMainScreen,
            modifier = Modifier
                .padding(D.Padding.paddingSmall)
                .fillMaxWidth(0.4f)
        )
    }
}