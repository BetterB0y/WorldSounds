package pl.polsl.worldsounds.screen.main_menu

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import pl.polsl.worldsounds.base.BaseViewModel
import pl.polsl.worldsounds.base.Event
import pl.polsl.worldsounds.screen.destinations.BestScoresScreenDestination
import pl.polsl.worldsounds.screen.destinations.GameModeScreenDestination
import pl.polsl.worldsounds.screen.destinations.SettingsScreenDestination
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor(
    coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<MainMenuScreenState>(coroutineDispatcher) {
    override val initialState: MainMenuScreenState = MainMenuScreenState.InitialState
    override val _state: MutableStateFlow<MainMenuScreenState> = MutableStateFlow(initialState)

    fun navigateToGameModeScreen() = launch {
        sendEvent(MainMenuEvent.OpenGameModeScreen)
    }

    fun navigateToBestScoresScreen() = launch {
        sendEvent(MainMenuEvent.OpenBestScoresScreen)
    }

    fun navigateToSettings() = launch {
        sendEvent(MainMenuEvent.OpenSettingsScreen)
    }
}

sealed class MainMenuEvent {
    object OpenGameModeScreen : Event.Navigation(GameModeScreenDestination)
    object OpenSettingsScreen : Event.Navigation(SettingsScreenDestination)
    object OpenBestScoresScreen : Event.Navigation(BestScoresScreenDestination)
}

sealed class MainMenuScreenState {
    data object InitialState : MainMenuScreenState()
}
