package pl.polsl.worldsounds.screen.best_score

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import pl.polsl.worldsounds.base.observeState
import pl.polsl.worldsounds.models.GameModeData

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
    Column(modifier = Modifier.fillMaxSize()) {
        Row {
            Button(onClick = { changeGameMode(GameModeData.OnePicture) }) {
                Text(text = "GameMode.OnePicture")
            }
            Button(onClick = { changeGameMode(GameModeData.OneSound) }) {
                Text(text = "GameMode.OneSound")
            }
        }
        LazyColumn {
            items(
                items = state.scores,
                key = { score -> score.id }
            ) { score ->
                Text(text = "${score.categoryName} - ${score.playerName} - ${score.score}")
            }
        }
    }
}