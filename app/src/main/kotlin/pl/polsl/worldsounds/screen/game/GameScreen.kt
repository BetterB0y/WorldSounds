package pl.polsl.worldsounds.screen.game

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import pl.polsl.worldsounds.ui.components.buttons.GameNavButtons
import pl.polsl.worldsounds.ui.components.dialogs.ExitGameDialog

@Composable
fun GameScreen(
    state: GameScreenState,
    selectedName: String,
    resetSelectedName: () -> Unit,
    navigateToMainScreen: () -> Unit,
    processAnswer: (String, Boolean, (Boolean) -> Unit) -> Unit,
    content: @Composable () -> Unit
) {
    val isFirstTry = remember {
        mutableMapOf<Int, Boolean>().apply {
            (1..state.numberOfRounds).forEach { this[it] = true }
        }
    }

    var isExitDialogVisible: Boolean by remember {
        mutableStateOf(false)
    }

    BackHandler {
        isExitDialogVisible = true
    }


    if (isExitDialogVisible) {
        ExitGameDialog(onConfirm = { navigateToMainScreen() }, onDismiss = { isExitDialogVisible = false })
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        content()
        GameNavButtons(
            onExit = { isExitDialogVisible = true },
            onNext = {
                processAnswer(it, isFirstTry[state.currentRound]!!) { isCorrect ->
                    isFirstTry[state.currentRound] = false
                    if (isCorrect) resetSelectedName()
                }
            },
            selectedName = selectedName
        )
    }

}