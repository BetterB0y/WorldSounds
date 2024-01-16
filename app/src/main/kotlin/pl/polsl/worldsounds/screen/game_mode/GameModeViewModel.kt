package pl.polsl.worldsounds.screen.game_mode

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import pl.polsl.worldsounds.base.BaseViewModel
import pl.polsl.worldsounds.base.Event
import pl.polsl.worldsounds.domain.usecases.SaveGameModeUseCase
import pl.polsl.worldsounds.models.GameModeData
import pl.polsl.worldsounds.models.mappers.toModel
import pl.polsl.worldsounds.screen.destinations.CategoryScreenDestination
import javax.inject.Inject


@HiltViewModel
class GameModeViewModel @Inject constructor(
    private val _saveGameModeUseCase: SaveGameModeUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<GameModeScreenState>(coroutineDispatcher) {
    override val initialState: GameModeScreenState = GameModeScreenState.InitialState
    override val _state: MutableStateFlow<GameModeScreenState> = MutableStateFlow(initialState)

    fun saveAndNavigate(gameMode: GameModeData) = launch {
        _saveGameModeUseCase(gameMode.toModel())
        sendEvent(GameModeEvent.OpenCategoryScreen)
    }

    fun navigateBack() = launch {
        sendEvent(Event.Navigation.NavigateBack)
    }
}

sealed class GameModeEvent {
    object OpenCategoryScreen : Event.Navigation(CategoryScreenDestination)
}

sealed class GameModeScreenState {
    data object InitialState : GameModeScreenState()
}