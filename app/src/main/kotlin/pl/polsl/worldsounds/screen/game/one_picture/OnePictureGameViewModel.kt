package pl.polsl.worldsounds.screen.game.one_picture

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
class OnePictureGameViewModel @Inject constructor(
    private val _getCategoryUseCase: GetCategoryUseCase,
    private val _getRoundsAssetsUseCase: GetRoundsAssetsUseCase,
    private val _getNumberOfRoundsUseCase: GetNumberOfRoundsUseCase,
    saveScoreUseCase: SaveScoreUseCase,
    saveAudioToPlayUseCase: SaveAudioToPlayUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : GameViewModel<OnePictureGameScreenState>(saveScoreUseCase, saveAudioToPlayUseCase, coroutineDispatcher) {
    private val _roundsAssets: MutableStateFlow<List<RoundAssetsData.OnePicture>> =
        MutableStateFlow(emptyList())

    override val initialState: OnePictureGameScreenState = OnePictureGameScreenState.InitialState
    override val _state: Flow<OnePictureGameScreenState> = combine(
        category,
        _roundsAssets,
        currentRound,
        numberOfRounds,
        score,
    ) { category, roundsAssets, currentRound, numberOfRounds, score ->
        if (currentRound != state.value.currentRound) {
            saveAudio(null)
        }
        OnePictureGameScreenState.ReadyState(
            category,
            roundsAssets,
            currentRound,
            numberOfRounds,
            score
        )
    }

    init {
        launch {
            saveAudioToPlayUseCase("")
            val categoryData = _getCategoryUseCase(Unit).toData()
            category.update {
                categoryData
            }
            numberOfRounds.update {
                _getNumberOfRoundsUseCase(Unit)
            }
            _roundsAssets.update {
                _getRoundsAssetsUseCase(
                    GetRoundsAssetsUseCase.Params.OnePicture(
                        categoryData.id,
                        numberOfRounds.value
                    )
                ).map { it.toData(categoryData.name) as RoundAssetsData.OnePicture }
            }
        }
    }

    override fun hideWrongAsset(answer: String) {
        _roundsAssets.update {
            _roundsAssets.value.toMutableList().apply {
                set(currentRound.value - 1, state.value.currentRoundData.hideAudio(answer))
            }
        }
    }
}

sealed class OnePictureGameScreenState : GameScreenState() {
    abstract override val roundData: List<RoundAssetsData.OnePicture>
    abstract override val currentRoundData: RoundAssetsData.OnePicture

    data object InitialState : OnePictureGameScreenState() {
        override val category: CategoryData = CategoryData.default()
        override val roundData: List<RoundAssetsData.OnePicture> = emptyList()
        override val currentRound: Int = 1
        override val numberOfRounds: Int = 0
        override val score: Int = 0
        override val currentRoundData: RoundAssetsData.OnePicture
            get() = RoundAssetsData.OnePicture.default()
    }

    data class ReadyState(
        override val category: CategoryData,
        override val roundData: List<RoundAssetsData.OnePicture>,
        override val currentRound: Int,
        override val numberOfRounds: Int,
        override val score: Int,
    ) : OnePictureGameScreenState() {
        override val currentRoundData: RoundAssetsData.OnePicture
            get() = roundData.getOrElse(currentRound - 1) { RoundAssetsData.OnePicture.default() }
    }
}

