package pl.polsl.worldsounds.screen.game

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import pl.polsl.worldsounds.base.BaseViewModel
import pl.polsl.worldsounds.domain.usecases.GetCategoryIdUseCase
import pl.polsl.worldsounds.domain.usecases.GetGameModeUseCase
import pl.polsl.worldsounds.models.GameModeData
import pl.polsl.worldsounds.models.mappers.toData
import javax.inject.Inject


@HiltViewModel
class GameViewModel @Inject constructor(
    private val _getGameModeUseCase: GetGameModeUseCase,
    private val _getCategoryIdUseCase: GetCategoryIdUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<GameScreenState>(coroutineDispatcher) {
    override val initialState: GameScreenState = GameScreenState.InitialState
    override val _state: MutableStateFlow<GameScreenState> = MutableStateFlow(initialState)

    init {
        launch {
            val gameMode = _getGameModeUseCase(Unit)
            val categoryId = _getCategoryIdUseCase(Unit)
            _state.value = GameScreenState.ReadyState(gameMode.toData(), categoryId)
        }
    }
}

sealed class GameScreenState {
    abstract val gameMode: GameModeData
    abstract val categoryId: Long

    data object InitialState : GameScreenState() {
        override val gameMode: GameModeData = GameModeData.OnePicture
        override val categoryId: Long = -1L
    }

    data class ReadyState(
        override val gameMode: GameModeData,
        override val categoryId: Long,
    ) : GameScreenState()
}
