package pl.polsl.worldsounds.screen.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.polsl.worldsounds.base.Event
import pl.polsl.worldsounds.base.observeEvents
import pl.polsl.worldsounds.ui.components.MainButton

@Destination
@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {

    viewModel.events.observeEvents {
        when (it) {
            is Event.Navigation -> it.navigate(navigator)
        }
    }


    GameModeScreen(
        saveAndNavigate = viewModel::saveAndNavigate
    )
}

@Composable
private fun GameModeScreen(
    saveAndNavigate: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        MainButton(text = "Kategoria 1", onClick = { saveAndNavigate() })
        MainButton(text = "Kategoria 2", onClick = {})
        MainButton(text = "Kategoria 3", onClick = { })
    }
}