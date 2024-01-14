package pl.polsl.worldsounds.screen.best_score

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import pl.polsl.worldsounds.R
import pl.polsl.worldsounds.base.observeState
import pl.polsl.worldsounds.models.GameModeData
import pl.polsl.worldsounds.ui.components.buttons.base.PrimaryButton
import pl.polsl.worldsounds.ui.resources.D

@Destination
@Composable
fun BestScoresScreen(
    viewModel: BestScoresViewModel = hiltViewModel(),
) {
    val state by viewModel.observeState()

    BestScoresScreen(
        state = state,
        changeGameMode = viewModel::changeGameMode,
    )
}


@Composable
private fun BestScoresScreen(
    state: BestScoreScreenState,
    changeGameMode: (GameModeData) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            PrimaryButton(
                text = "GameMode.OnePicture",
                modifier = Modifier.padding(D.Padding.paddingSmall)
            ) {
                changeGameMode(GameModeData.OnePicture)
            }
            PrimaryButton(
                text = "GameMode.OneSound",
                modifier = Modifier.padding(D.Padding.paddingSmall)
            ) {
                changeGameMode(GameModeData.OneSound)
            }
        }
        if (state.scores.isEmpty()) {
            Text("Brak wyników")
        } else {
            LazyColumn {
                items(
                    items = state.scores,
                    key = { score -> score.id }
                ) { score ->
                    val playerName =
                        if (score.isDefaultUsername) stringResource(R.string.defaultUsername) else score.playerName
                    Text(text = "${score.categoryName} - $playerName - ${score.score}")
                }
            }
        }
    }
}