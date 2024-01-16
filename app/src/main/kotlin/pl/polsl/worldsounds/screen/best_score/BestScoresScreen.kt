package pl.polsl.worldsounds.screen.best_score

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.polsl.worldsounds.R
import pl.polsl.worldsounds.base.Event
import pl.polsl.worldsounds.base.observeEvents
import pl.polsl.worldsounds.base.observeState
import pl.polsl.worldsounds.models.GameModeData
import pl.polsl.worldsounds.ui.components.buttons.GlobalGoBackButton
import pl.polsl.worldsounds.ui.components.buttons.PrimaryButton
import pl.polsl.worldsounds.ui.resources.D

@Destination
@Composable
fun BestScoresScreen(
    viewModel: BestScoresViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val state by viewModel.observeState()

    viewModel.events.observeEvents {
        when (it) {
            is Event.Navigation -> it.navigate(navigator)
        }
    }

    BestScoresScreen(
        state = state,
        changeGameMode = viewModel::changeGameMode,
        navigateBack = viewModel::navigateBack,
    )
}

@Composable
private fun BestScoresScreen(
    state: BestScoreScreenState,
    changeGameMode: (GameModeData) -> Unit,
    navigateBack: () -> Unit,
) {
    GlobalGoBackButton(navigateBack)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(D.Padding.paddingSmall),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            PrimaryButton(
                icon = GameModeData.OnePicture.icon,
                iconDescription = GameModeData.OnePicture.iconDescription,
                modifier = Modifier.padding(D.Padding.paddingSmall)
            ) {
                changeGameMode(GameModeData.OnePicture)
            }
            PrimaryButton(
                icon = GameModeData.OneSound.icon,
                iconDescription = GameModeData.OneSound.iconDescription,
                modifier = Modifier.padding(D.Padding.paddingSmall)
            ) {
                changeGameMode(GameModeData.OneSound)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .fillMaxHeight(0.9f)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
        ) {
            if (state.scores.isEmpty()) {
                Text(stringResource(R.string.noScores), modifier = Modifier.padding(D.Padding.paddingSmall))
            } else {
                LazyColumn(
                    modifier = Modifier.padding(D.Padding.paddingSmall),
                ) {
                    item {
                        TableHeader()
                        BorderLine()
                    }
                    items(
                        items = state.scores,
                        key = { score -> score.id },
                    ) { score ->
                        val playerName =
                            if (score.isDefaultUsername) stringResource(R.string.defaultUsername) else score.playerName
                        TableRow(score.categoryName, playerName, score.score.toString())
                    }
                    item {
                        BorderLine()
                    }
                }
            }
        }

    }
}

@Composable
private fun BorderLine() {
    Box(
        modifier =
        Modifier
            .height(1.dp)
            .fillMaxWidth()
            .border(1.dp, Color.Black)
    )
}

@Composable
private fun TableHeader() {
    TableRow(
        categoryName = "Kategoria",
        playerName = "Gracz",
        score = "Wynik",
        isHeader = true,
    )
}

@Composable
private fun TableRow(categoryName: String, playerName: String, score: String, isHeader: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = categoryName,
            fontWeight = if (isHeader) FontWeight.Bold else FontWeight.Normal,
            modifier =
            Modifier
                .weight(0.4f)
                .padding(2.dp)
        )
        Text(
            text = playerName,
            fontWeight = if (isHeader) FontWeight.Bold else FontWeight.Normal,
            modifier =
            Modifier
                .weight(0.4f)
                .padding(2.dp)
        )
        Text(
            text = score,
            fontWeight = if (isHeader) FontWeight.Bold else FontWeight.Normal,
            modifier =
            Modifier
                .weight(0.2f)
                .padding(2.dp)
        )
    }
}