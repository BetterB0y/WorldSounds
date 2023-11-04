package pl.polsl.worldsounds.screen.game.one_picture

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
class OnePictureGameViewModel @Inject constructor(
    private val _getCategoryUseCase: GetCategoryUseCase,
    private val _getRoundAssetsUseCase: GetRoundAssetsUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : GameViewModel<OnePictureGameScreenState>(coroutineDispatcher) {
    override val initialState: OnePictureGameScreenState = OnePictureGameScreenState.InitialState
    override val _state: MutableStateFlow<OnePictureGameScreenState> = MutableStateFlow(initialState)

    init {
        launch {
            val category = _getCategoryUseCase(Unit)
            val roundAssets =
                _getRoundAssetsUseCase(GetRoundAssetsUseCase.Params(category.id, GameModeModel.OnePicture))
            _state.value = OnePictureGameScreenState.ReadyState(
                category.id,
                roundAssets.toData(category.name) as RoundAssetsData.OnePicture
            )
        }
    }

    override fun correctAnswer(answer: String) {

    }

    override fun incorrectAnswer(answer: String) {
        val state = state.value
        if (state is OnePictureGameScreenState.ReadyState) {
            val assets = state.roundAssets
            _state.value = state.copy(
                roundAssets = (assets.copy(audios = assets.audios.map {
                    if (it.file.nameWithoutExtension == answer) it.copy(
                        isHidden = true
                    ) else it
                }))
            )
        }
    }
}

sealed class OnePictureGameScreenState : GameScreenState() {
    abstract override val categoryId: Long
    abstract override val roundAssets: RoundAssetsData.OnePicture

    data object InitialState : OnePictureGameScreenState() {
        override val categoryId: Long = -1L
        override val roundAssets: RoundAssetsData.OnePicture = RoundAssetsData.OnePicture.default()
    }

    data class ReadyState(
        override val categoryId: Long,
        override val roundAssets: RoundAssetsData.OnePicture
    ) : OnePictureGameScreenState()
}
