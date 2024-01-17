package pl.polsl.worldsounds.screen.game

import android.content.Context
import androidx.core.net.toUri
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import pl.polsl.worldsounds.base.BaseViewModel
import pl.polsl.worldsounds.base.Event
import pl.polsl.worldsounds.domain.usecases.SaveAudioToPlayUseCase
import pl.polsl.worldsounds.domain.usecases.SaveScoreUseCase
import pl.polsl.worldsounds.models.CategoryData
import pl.polsl.worldsounds.models.RoundAssetsData
import pl.polsl.worldsounds.screen.destinations.SummaryScreenDestination
import pl.polsl.worldsounds.utils.AudioPlayer
import java.io.File

abstract class GameViewModel<out STATE : GameScreenState>(
    private val _saveScoreUseCase: SaveScoreUseCase,
    private val _saveAudioToPlayUseCase: SaveAudioToPlayUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<STATE>(coroutineDispatcher) {
    protected val score: MutableStateFlow<Int> =
        MutableStateFlow(0)

    protected val category: MutableStateFlow<CategoryData> =
        MutableStateFlow(CategoryData.default())

    protected val currentRound: MutableStateFlow<Int> =
        MutableStateFlow(1)

    protected val numberOfRounds: MutableStateFlow<Int> =
        MutableStateFlow(1)

    protected val isFirstTry: MutableStateFlow<Boolean> =
        MutableStateFlow(true)


    protected abstract fun hideWrongAsset(answer: String)

    fun processAnswer(answer: String, result: (Boolean) -> Unit) = launch {
        AudioPlayer.stopAudio()
        if (answer.isEmpty()) {
            result(false)
            return@launch
        }
        if (!state.value.isAnswerCorrect(answer)) {
            isFirstTry.update { false }
            hideWrongAsset(answer)
            result(false)
            return@launch
        }

        if (state.value.isFirstTry) {
            updateScore()
        }
        if (state.value.isGameFinished) {
            saveScore()
            sendEvent(GameEvent.OpenSummaryScreen(score.value))
            return@launch
        }

        changeRound()
        result(true)
    }

    private fun updateScore() {
        score.update { it + 1 }
    }

    private suspend fun saveScore() {
        _saveScoreUseCase(
            SaveScoreUseCase.Params(
                score = score.value,
                categoryName = category.value.name
            )
        )
    }

    private fun changeRound() {
        AudioPlayer.stopAudio()
        isFirstTry.update { true }
        currentRound.update { it + 1 }
    }

    fun navigateToMainScreen() = launch {
        sendEvent(Event.Navigation.NavigateToMainMenu)
    }

    fun playAudio(context: Context, audio: File) {
        AudioPlayer.playAudio(context, audio.toUri())
    }


    fun saveAudio(audio: File?) = launch {
        _saveAudioToPlayUseCase(audio?.toString() ?: "")
    }

    override fun onCleared() {
        super.onCleared()
        AudioPlayer.stopAudio()
    }
}

sealed class GameEvent {
    class OpenSummaryScreen(score: Int) : Event.Navigation(SummaryScreenDestination(score))
}

abstract class GameScreenState {
    abstract val category: CategoryData
    abstract val roundData: List<RoundAssetsData>
    abstract val currentRound: Int
    abstract val numberOfRounds: Int
    abstract val score: Int
    abstract val isFirstTry: Boolean

    abstract val currentRoundData: RoundAssetsData
    val isGameFinished get() = numberOfRounds == currentRound
    fun isAnswerCorrect(answer: String) = currentRoundData.answerFileName == answer
}