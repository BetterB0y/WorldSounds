package pl.polsl.worldsounds.screen.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.polsl.worldsounds.base.Event
import pl.polsl.worldsounds.base.observeEvents
import pl.polsl.worldsounds.base.observeState
import pl.polsl.worldsounds.ui.components.ImageCard

@Destination
@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val state by viewModel.observeState()

    viewModel.events.observeEvents {
        when (it) {
            is Event.Navigation -> it.navigate(navigator)
        }
    }


    CategoryScreen(
        state = state,
        saveAndNavigate = viewModel::saveAndNavigate
    )
}

@Composable
private fun CategoryScreen(
    state: CategoryScreenState,
    saveAndNavigate: () -> Unit,
) {
    LazyRow(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(
            items = state.categories,
            key = { it.name }) {
            ImageCard(modifier = Modifier.padding(10.dp), file = it.image) {
                saveAndNavigate()
            }
        }
    }
}