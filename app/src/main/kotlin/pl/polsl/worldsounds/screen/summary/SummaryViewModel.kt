package pl.polsl.worldsounds.screen.summary

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import pl.polsl.worldsounds.base.BaseViewModel
import pl.polsl.worldsounds.base.Event
import pl.polsl.worldsounds.domain.models.GameModeModel
import pl.polsl.worldsounds.domain.usecases.GetGameModeUseCase
import pl.polsl.worldsounds.screen.category.CategoryEvent
import pl.polsl.worldsounds.screen.destinations.OnePictureGameScreenDestination
import pl.polsl.worldsounds.screen.destinations.OneSoundGameScreenDestination
import javax.inject.Inject

@HiltViewModel
class SummaryViewModel @Inject constructor(
    private val _getGameModeUseCase: GetGameModeUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<SummaryScreenState>(coroutineDispatcher) {
    override val initialState: SummaryScreenState = SummaryScreenState.InitialState
    override val _state: MutableStateFlow<SummaryScreenState> = MutableStateFlow(initialState)

    fun navigateToMainScreen() = launch {
        sendEvent(Event.Navigation.NavigateToMainMenu)
    }

    fun startGameAgain() = launch {
        sendEvent(
            when (_getGameModeUseCase(Unit)) {
                GameModeModel.OnePicture -> CategoryEvent.OpenOnePictureGame
                GameModeModel.OneSound -> CategoryEvent.OpenOneSoundGame
            }
        )
    }
}

sealed class SummaryEvent {
    object OpenOnePictureGame : Event.Navigation(OnePictureGameScreenDestination)
    object OpenOneSoundGame : Event.Navigation(OneSoundGameScreenDestination)
}

sealed class SummaryScreenState {
    data object InitialState : SummaryScreenState()
}