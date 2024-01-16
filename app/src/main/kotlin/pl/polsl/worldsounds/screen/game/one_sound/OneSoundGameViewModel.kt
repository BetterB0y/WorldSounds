package pl.polsl.worldsounds.screen.game.one_sound

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import pl.polsl.worldsounds.domain.usecases.GetCategoryUseCase
import pl.polsl.worldsounds.domain.usecases.GetNumberOfRoundsUseCase
import pl.polsl.worldsounds.domain.usecases.GetRoundsAssetsUseCase
import pl.polsl.worldsounds.domain.usecases.SaveAudioToPlayUseCase
import pl.polsl.worldsounds.domain.usecases.SaveScoreUseCase
import pl.polsl.worldsounds.models.CategoryData
import pl.polsl.worldsounds.models.RoundAssetsData
import pl.polsl.worldsounds.models.mappers.toData
import pl.polsl.worldsounds.screen.game.GameScreenState
import pl.polsl.worldsounds.screen.game.GameViewModel
import javax.inject.Inject

@HiltViewModel
class OneSoundGameViewModel @Inject constructor(
    private val _getCategoryUseCase: GetCategoryUseCase,
    private val _getRoundsAssetsUseCase: GetRoundsAssetsUseCase,
    private val _getNumberOfRoundsUseCase: GetNumberOfRoundsUseCase,
    saveScoreUseCase: SaveScoreUseCase,
    saveAudioToPlayUseCase: SaveAudioToPlayUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : GameViewModel<OneSoundGameScreenState>(saveScoreUseCase, saveAudioToPlayUseCase, coroutineDispatcher) {
    private val _roundsAssets: MutableStateFlow<List<RoundAssetsData.OneSound>> =
        MutableStateFlow(emptyList())

    override val initialState: OneSoundGameScreenState = OneSoundGameScreenState.InitialState
    override val _state: Flow<OneSoundGameScreenState> = combine(
        category,
        _roundsAssets,
        currentRound,
        numberOfRounds,
        score,
    ) { category, roundsAssets, currentRound, numberOfRounds, score ->
        if (currentRound != state.value.currentRound) {
            //todo exception
            saveAudio(_roundsAssets.value[currentRound - 1].audio.file)
        }
        OneSoundGameScreenState.ReadyState(
            category,
            roundsAssets,
            currentRound,
            numberOfRounds,
            score
        )
    }

    init {
        launch {
            val categoryData = _getCategoryUseCase(Unit).toData()
            category.update {
                categoryData
            }
            numberOfRounds.update {
                _getNumberOfRoundsUseCase(Unit)
            }
            _roundsAssets.update {
                _getRoundsAssetsUseCase(
                    GetRoundsAssetsUseCase.Params.OneSound(
                        categoryData.id,
                        numberOfRounds.value
                    )
                ).map { it.toData(categoryData.name) as RoundAssetsData.OneSound }
            }
        }
    }

    override fun hideWrongAsset(answer: String) {
        _roundsAssets.update {
            _roundsAssets.value.toMutableList().apply {
                set(currentRound.value - 1, state.value.currentRoundData.hideImage(answer))
            }
        }
    }
}


sealed class OneSoundGameScreenState : GameScreenState() {
    abstract override val roundData: List<RoundAssetsData.OneSound>
    abstract override val currentRoundData: RoundAssetsData.OneSound

    data object InitialState : OneSoundGameScreenState() {
        override val category: CategoryData = CategoryData.default()
        override val roundData: List<RoundAssetsData.OneSound> = emptyList()
        override val currentRound: Int = 0
        override val numberOfRounds: Int = 0
        override val score: Int = 0
        override val currentRoundData: RoundAssetsData.OneSound
            get() = RoundAssetsData.OneSound.default()
    }

    data class ReadyState(
        override val category: CategoryData,
        override val roundData: List<RoundAssetsData.OneSound>,
        override val currentRound: Int,
        override val numberOfRounds: Int,
        override val score: Int,
    ) : OneSoundGameScreenState() {
        override val currentRoundData: RoundAssetsData.OneSound
            get() = roundData.getOrElse(currentRound - 1) { RoundAssetsData.OneSound.default() }
    }
}
