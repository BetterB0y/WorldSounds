package pl.polsl.worldsounds.screen.game

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import pl.polsl.worldsounds.models.RoundAssetsData
import pl.polsl.worldsounds.ui.components.ImageCard
import java.io.File

@Composable
fun OnePictureGameScreen(
    state: GameScreenState,
    playAudio: (Context, File) -> Unit,
) {
    val context = LocalContext.current
    val (roundNumber, assets) = state.roundAssets!!
    assets as RoundAssetsData.OnePicture

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        ImageCard(modifier = Modifier.padding(10.dp), file = assets.image.file) {}
        LazyRow(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items(
                items = assets.audios,
                key = { it.file }) {
                Button(onClick = { playAudio(context, it.file) }) {
                    Text("Play")
                }
            }
        }
    }
}