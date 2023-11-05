package pl.polsl.worldsounds.screen.game

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import pl.polsl.worldsounds.base.BaseViewModel
import pl.polsl.worldsounds.base.Event
import pl.polsl.worldsounds.models.CategoryData
import pl.polsl.worldsounds.models.RoundAssetsData
import pl.polsl.worldsounds.screen.destinations.MainMenuScreenDestination
import java.io.File

abstract class GameViewModel<out STATE : GameScreenState>(
    coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<STATE>(coroutineDispatcher) {
    private var mediaPlayer: MediaPlayer? = null
    protected val score: MutableStateFlow<Int> =
        MutableStateFlow(0)

    protected val category: MutableStateFlow<CategoryData> =
        MutableStateFlow(CategoryData.default())

    protected val currentRound: MutableStateFlow<Int> =
        MutableStateFlow(1)

    protected val numberOfRounds: MutableStateFlow<Int> =
        MutableStateFlow(1)

    protected abstract fun hideWrongAsset(answer: String)

    fun processAnswer(answer: String, isFirstTry: Boolean, result: (Boolean) -> Unit) = launch {
        stopAudio()
        if (answer.isEmpty()) {
            result(false)
            return@launch
        }
        if (!state.value.isAnswerCorrect(answer)) {
            hideWrongAsset(answer)
            result(false)
            return@launch
        }

        if (isFirstTry) {
            updateScore()
        }
        if (state.value.isGameFinished) {
            saveScore()
            sendEvent(GameEvent.OpenScoreScreen)
            return@launch
        }

        changeRound()
        result(true)
    }

    private fun updateScore() {
        score.update { it + 1 }
    }

    private fun saveScore() {
        //TODO save score
    }

    private fun changeRound() {
        currentRound.update { it + 1 }
    }

    fun navigateToMainScreen() = launch {
        sendEvent(GameEvent.OpenMainMenuScreen)
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
    object OpenScoreScreen : Event.Navigation(MainMenuScreenDestination)
}

abstract class GameScreenState {
    abstract val category: CategoryData
    abstract val roundData: List<RoundAssetsData>
    abstract val currentRound: Int
    abstract val numberOfRounds: Int
    abstract val score: Int

    abstract val currentRoundData: RoundAssetsData
    val isGameFinished get() = numberOfRounds == currentRound
    fun isAnswerCorrect(answer: String) = currentRoundData.answerFileName == answer
}