package pl.polsl.worldsounds.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import pl.polsl.worldsounds.ui.components.MainButton

@RootNavGraph(start = true)
@Destination
@Composable
fun MainMenuScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        MainButton(text = "Graj", onClick = {})
        MainButton(text = "Ustawienia", onClick = {})
    }
}