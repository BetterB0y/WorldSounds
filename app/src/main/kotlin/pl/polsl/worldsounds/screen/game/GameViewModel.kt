package pl.polsl.worldsounds.screen.game

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import kotlinx.coroutines.CoroutineDispatcher
import pl.polsl.worldsounds.base.BaseViewModel
import pl.polsl.worldsounds.base.Event
import pl.polsl.worldsounds.models.RoundAssetsData
import pl.polsl.worldsounds.screen.destinations.MainMenuScreenDestination
import timber.log.Timber
import java.io.File

abstract class GameViewModel<out STATE : GameScreenState>(
    coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<STATE>(coroutineDispatcher) {
    private var mediaPlayer: MediaPlayer? = null

    protected abstract fun incorrectAnswer(answer: String)
    abstract fun correctAnswer(answer: String)


    fun navigateToMainScreen() = launch {
        sendEvent(GameEvent.OpenMainMenuScreen)
    }

    fun processAnswer(answer: String) {
        if (answer.isEmpty()) return
        if (answer == state.value.roundAssets.answerFileName) {
            Timber.e("Answer correct")
            correctAnswer(answer)
        } else {
            Timber.e("Answer incorrect")
            incorrectAnswer(answer)
        }
    }

    fun playAudio(context: Context, audio: File) {
        stopAudio()
        mediaPlayer = MediaPlayer.create(context, audio.toUri()).apply {
            start()
        }
    }

    private fun stopAudio() {
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

abstract class GameScreenState {
    abstract val categoryId: Long
    abstract val roundAssets: RoundAssetsData
}