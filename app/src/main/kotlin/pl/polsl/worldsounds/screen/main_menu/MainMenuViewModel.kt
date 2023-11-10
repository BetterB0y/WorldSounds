package pl.polsl.worldsounds.screen.main_menu

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import pl.polsl.worldsounds.R
import pl.polsl.worldsounds.base.BaseViewModel
import pl.polsl.worldsounds.base.Event
import pl.polsl.worldsounds.models.RiddleData
import pl.polsl.worldsounds.screen.destinations.GameModeScreenDestination
import pl.polsl.worldsounds.screen.destinations.SettingsScreenDestination
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor(
    coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<MainMenuScreenState>(coroutineDispatcher) {

    private val _riddleData: MutableStateFlow<RiddleData> =
        MutableStateFlow(RiddleData.default())

    override val initialState: MainMenuScreenState = MainMenuScreenState.InitialState
    override val _state: Flow<MainMenuScreenState> = _riddleData.map {
        MainMenuScreenState.ReadyState(it)
    }

    fun navigateToGameModeScreen() = launch {
        sendEvent(MainMenuEvent.OpenGameModeScreen)
    }

    fun processRiddleAnswer(answer: String) = launch {
        if (answer.isEmpty()) {
            sendEvent(MainMenuEvent.EmptyAnswer)
            return@launch
        }
        if (!state.value.riddleData.isAnswerCorrect(answer)) {
            sendEvent(MainMenuEvent.WrongAnswer)
            return@launch
        }
        sendEvent(MainMenuEvent.OpenSettingsScreen)
    }

    fun generateRiddle() {
        _riddleData.update { RiddleData.generate() }
    }
}

sealed class MainMenuEvent {
    object OpenGameModeScreen : Event.Navigation(GameModeScreenDestination)
    object OpenSettingsScreen : Event.Navigation(SettingsScreenDestination)
    object WrongAnswer : Event.Message(R.string.riddleWrongAnswer)
    object EmptyAnswer : Event.Message(R.string.riddleEmptyAnswer)
}

sealed class MainMenuScreenState {
    abstract val riddleData: RiddleData

    data object InitialState : MainMenuScreenState() {
        override val riddleData: RiddleData = RiddleData.default()
    }

    data class ReadyState(
        override val riddleData: RiddleData
    ) : MainMenuScreenState()
}