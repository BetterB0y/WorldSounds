package pl.polsl.worldsounds.screen.game_mode

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import pl.polsl.worldsounds.app.Config
import pl.polsl.worldsounds.base.BaseViewModel
import pl.polsl.worldsounds.base.Event

import pl.polsl.worldsounds.data.models.GameModeUi
import pl.polsl.worldsounds.data.models.mappers.toModel
import pl.polsl.worldsounds.domain.usecases.SaveGameModeUseCase
import pl.polsl.worldsounds.screen.destinations.CategoryScreenDestination

import javax.inject.Inject


@HiltViewModel
class GameModeViewModel @Inject constructor(
    private val _saveGameModeUseCase: SaveGameModeUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<GameModeScreenState>(coroutineDispatcher) {
    override val initialState: GameModeScreenState = GameModeScreenState.InitialState
    override val _state: MutableStateFlow<GameModeScreenState> = MutableStateFlow(initialState)

    fun saveAndNavigate(gameMode: GameModeUi) = launch {
        _saveGameModeUseCase(gameMode.toModel())
        sendEvent(GameModeEvent.OpenCategoryScreen)
    }

    private var mediaPlayer: MediaPlayer? = null

    fun playAudio(context: Context, audioPath: String) {
        stopAudio() // stop current playing audio if any
        mediaPlayer = MediaPlayer.create(context, Uri.parse(Config.basePath + audioPath)).apply {
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

sealed class GameModeEvent {
    object OpenCategoryScreen : Event.Navigation(CategoryScreenDestination)
}

sealed class GameModeScreenState {
    data object InitialState : GameModeScreenState()
}