package pl.polsl.worldsounds.screen.game.one_sound

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import pl.polsl.worldsounds.domain.models.GameModeModel
import pl.polsl.worldsounds.domain.usecases.GetCategoryUseCase
import pl.polsl.worldsounds.domain.usecases.GetRoundAssetsUseCase
import pl.polsl.worldsounds.models.RoundAssetsData
import pl.polsl.worldsounds.models.mappers.toData
import pl.polsl.worldsounds.screen.game.GameScreenState
import pl.polsl.worldsounds.screen.game.GameViewModel
import javax.inject.Inject


@HiltViewModel
class OneSoundGameViewModel @Inject constructor(
    private val _getCategoryUseCase: GetCategoryUseCase,
    private val _getRoundAssetsUseCase: GetRoundAssetsUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : GameViewModel<OneSoundGameScreenState>(coroutineDispatcher) {


    override val initialState: OneSoundGameScreenState = OneSoundGameScreenState.InitialState
    override val _state: MutableStateFlow<OneSoundGameScreenState> = MutableStateFlow(initialState)

    init {
        launch {
            val category = _getCategoryUseCase(Unit)
            val roundAssets = _getRoundAssetsUseCase(GetRoundAssetsUseCase.Params(category.id, GameModeModel.OneSound))
            _state.value = OneSoundGameScreenState.ReadyState(
                category.id,
                roundAssets.toData(category.name) as RoundAssetsData.OneSound
            )
        }
    }

    override fun correctAnswer(answer: String) {

    }

    override fun incorrectAnswer(answer: String) {
        val state = state.value
        if (state is OneSoundGameScreenState.ReadyState) {
            _state.value = state.copy(
                roundAssets = (state.roundAssets.copy(images = state.roundAssets.images.map {
                    if (it.file.nameWithoutExtension == answer) it.copy(
                        isHidden = true
                    ) else it
                }))
            )
        }
    }
}


sealed class OneSoundGameScreenState : GameScreenState() {
    abstract override val categoryId: Long
    abstract override val roundAssets: RoundAssetsData.OneSound

    data object InitialState : OneSoundGameScreenState() {
        override val categoryId: Long = -1L
        override val roundAssets: RoundAssetsData.OneSound = RoundAssetsData.OneSound.default()
    }

    data class ReadyState(
        override val categoryId: Long,
        override val roundAssets: RoundAssetsData.OneSound
    ) : OneSoundGameScreenState()
}
