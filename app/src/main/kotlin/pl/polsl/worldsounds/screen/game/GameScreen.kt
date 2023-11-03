package pl.polsl.worldsounds.screen.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import pl.polsl.worldsounds.base.observeState

@Destination
@Composable
fun GameScreen(
    viewModel: GameViewModel = hiltViewModel(),
) {
    val state by viewModel.observeState()

    GameScreen(
        state = state,
    )
}

@Composable
private fun GameScreen(
    state: GameScreenState
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Kategoria ${state.categoryId}")
        Text(text = "Tryb ${state.gameMode}")
    }
}