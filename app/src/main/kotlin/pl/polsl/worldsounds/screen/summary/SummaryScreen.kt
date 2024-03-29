package pl.polsl.worldsounds.screen.summary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.polsl.worldsounds.R
import pl.polsl.worldsounds.base.Event
import pl.polsl.worldsounds.base.observeEvents
import pl.polsl.worldsounds.ui.components.buttons.PrimaryButton
import pl.polsl.worldsounds.ui.components.buttons.SecondaryButton
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
        Text(
            stringResource(R.string.congratulations),
            style = MaterialTheme.typography.titleLarge,

        )
        Text(stringResource(R.string.youScored, score))
        Spacer(modifier = Modifier.height(D.Padding.paddingDoubleBig))
        PrimaryButton(
            icon = R.drawable.play_again,
            iconDescription = R.string.iconPlayAgain,
            onClick = startGameAgain,
            modifier = Modifier
                .padding(D.Padding.paddingSmall)
                .width(D.Size.Width.button)
        )
        SecondaryButton(
            icon = R.drawable.home,
            iconDescription = R.string.iconHome,
            onClick = navigateToMainScreen,
            modifier = Modifier
                .padding(D.Padding.paddingSmall)
                .width(D.Size.Width.button)
        )
    }
}