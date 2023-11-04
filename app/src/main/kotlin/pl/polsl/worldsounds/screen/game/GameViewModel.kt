package pl.polsl.worldsounds.screen.game

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import pl.polsl.worldsounds.base.BaseViewModel
import pl.polsl.worldsounds.base.Event
import pl.polsl.worldsounds.domain.usecases.GetCategoryUseCase
import pl.polsl.worldsounds.domain.usecases.GetGameModeUseCase
import pl.polsl.worldsounds.domain.usecases.GetRoundAssetsUseCase
import pl.polsl.worldsounds.models.GameModeData
import pl.polsl.worldsounds.models.RoundAssetsData
import pl.polsl.worldsounds.models.mappers.toData
import pl.polsl.worldsounds.screen.destinations.MainMenuScreenDestination
import java.io.File
import javax.inject.Inject


@HiltViewModel
class GameViewModel @Inject constructor(
    private val _getGameModeUseCase: GetGameModeUseCase,
    private val _getCategoryUseCase: GetCategoryUseCase,
    private val _getRoundAssetsUseCase: GetRoundAssetsUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<GameScreenState>(coroutineDispatcher) {
    override val initialState: GameScreenState = GameScreenState.InitialState
    override val _state: MutableStateFlow<GameScreenState> = MutableStateFlow(initialState)
    private var mediaPlayer: MediaPlayer? = null

    init {
        launch {
            val gameMode = _getGameModeUseCase(Unit)
            val category = _getCategoryUseCase(Unit)
            val roundAssets = _getRoundAssetsUseCase(GetRoundAssetsUseCase.Params(category.id, gameMode))
            _state.value =
                GameScreenState.ReadyState(gameMode.toData(), category.id, 1 to roundAssets.toData(category.name))
        }
    }

    fun navigateToMainScreen() = launch {
        sendEvent(GameEvent.OpenMainMenuScreen)
    }


    fun playAudio(context: Context, audio: File) {
        stopAudio() // stop current playing audio if any
        mediaPlayer = MediaPlayer.create(context, audio.toUri()).apply {
            setOnCompletionListener {
                // Notify UI or perform an action when audio is complete
            }
            start()
        }
    }

    fun stopAudio() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onCleared() {
        super.onCleared()
        stopAudio()
    }
}

sealed class GameEvent {
    object OpenMainMenuScreen : Event.Navigation(MainMenuScreenDestination)
}

sealed class GameScreenState {
    abstract val gameMode: GameModeData
    abstract val categoryId: Long
    abstract val roundAssets: Pair<Int, RoundAssetsData>?

    data object InitialState : GameScreenState() {
        override val gameMode: GameModeData = GameModeData.OnePicture
        override val categoryId: Long = -1L
        override val roundAssets: Pair<Int, RoundAssetsData>? = null
    }

    data class ReadyState(
        override val gameMode: GameModeData,
        override val categoryId: Long,
        override val roundAssets: Pair<Int, RoundAssetsData>
    ) : GameScreenState()
}
