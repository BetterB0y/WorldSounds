package pl.polsl.worldsounds.screen.game

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import pl.polsl.worldsounds.ui.components.buttons.GameNavButtons
import pl.polsl.worldsounds.ui.components.dialogs.ExitGameDialog

@Composable
fun GameScreen(
    state: GameScreenState,
    selectedName: String,
    resetSelectedName: () -> Unit,
    navigateToMainScreen: () -> Unit,
    processAnswer: (Context, String, (Boolean) -> Unit) -> Unit,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
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
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        content()
        GameNavButtons(
            onExit = { isExitDialogVisible = true },
            onNext = {
                processAnswer(context, it) { isCorrect ->
                    if (isCorrect) resetSelectedName()
                }
            },
            selectedName = selectedName,
            currentRound = state.currentRound,
            maxRounds = state.numberOfRounds,
        )
    }

}