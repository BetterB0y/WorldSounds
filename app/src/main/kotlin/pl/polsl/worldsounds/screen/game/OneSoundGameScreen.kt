package pl.polsl.worldsounds.screen.game

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import pl.polsl.worldsounds.models.RoundAssetsData
import pl.polsl.worldsounds.ui.components.ImageCard
import timber.log.Timber
import java.io.File

@Composable
fun OneSoundGameScreen(
    state: GameScreenState,
    playAudio: (Context, File) -> Unit,
    navigateToMainScreen: () -> Unit,
) {
    val context = LocalContext.current
    val (roundNumber, assets) = state.roundAssets!!
    assets as RoundAssetsData.OneSound

    Timber.e("OneSoundGameScreen: $assets")

    var selectedId by remember { mutableStateOf(-1L) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(onClick = { playAudio(context, assets.audio.file) }) {
            Text("Play")
        }
        LazyRow(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items(
                items = assets.images,
                key = { it.file }) {
                ImageCard(modifier = Modifier.padding(10.dp), file = it.file, isSelected = selectedId == it.id) {
                    selectedId = it.id
                }
            }
        }
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { navigateToMainScreen() }) {
                Text("Wyjdź")
            }

            Button(onClick = { /*TODO*/ }) {
                Text("Zatwierdź")
            }

        }
    }
}
