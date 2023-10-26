package pl.polsl.worldsounds.screen.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import pl.polsl.worldsounds.R

@Destination
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Język")
        Text(text = stringResource(id = R.string.app_name))
        Row {
            Button(onClick = {
                viewModel.changeLanguage("pl")
            }) {
                Text("Polski")
            }
            Button(onClick = {
                viewModel.changeLanguage("en")
            }) {
                Text("Angielski")
            }
        }
    }
}
