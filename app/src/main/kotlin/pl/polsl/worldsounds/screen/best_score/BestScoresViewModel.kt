package pl.polsl.worldsounds.screen.best_score

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import pl.polsl.worldsounds.base.BaseViewModel
import pl.polsl.worldsounds.domain.usecases.ObserveScoresWithGameModeUseCase
import pl.polsl.worldsounds.models.BestScoreData
import pl.polsl.worldsounds.models.GameModeData
import pl.polsl.worldsounds.models.mappers.toData
import pl.polsl.worldsounds.models.mappers.toModel
import javax.inject.Inject


@HiltViewModel
class BestScoresViewModel @Inject constructor(
    observeScoresWithGameModeUseCase: ObserveScoresWithGameModeUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<BestScoreScreenState>(coroutineDispatcher) {

    private val _gameMode: MutableStateFlow<GameModeData> =
        MutableStateFlow(GameModeData.OnePicture)

    override val initialState: BestScoreScreenState = BestScoreScreenState.InitialState
    override val _state: Flow<BestScoreScreenState> =
        observeScoresWithGameModeUseCase(_gameMode.map { it.toModel() }).map {
            BestScoreScreenState.ReadyState(
                it.map { score -> score.toData() }
            )
        }

    fun changeGameMode(gameMode: GameModeData) {
        _gameMode.update { gameMode }
    }
}


sealed class BestScoreScreenState {
    abstract val scores: List<BestScoreData>

    data object InitialState : BestScoreScreenState() {
        override val scores: List<BestScoreData> = emptyList()
    }

    data class ReadyState(
        override val scores: List<BestScoreData>
    ) : BestScoreScreenState()
}
