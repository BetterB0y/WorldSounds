package pl.polsl.worldsounds.screen.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import pl.polsl.worldsounds.base.BaseViewModel
import pl.polsl.worldsounds.domain.usecases.GetNumberOfRoundsUseCase
import pl.polsl.worldsounds.domain.usecases.GetUsernameUseCase
import pl.polsl.worldsounds.domain.usecases.SaveNumberOfRoundsUseCase
import pl.polsl.worldsounds.domain.usecases.SaveUsernameUseCase
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val _getNumberOfRoundsUseCase: GetNumberOfRoundsUseCase,
    private val _getUsernameUseCase: GetUsernameUseCase,
    private val _saveNumberOfRoundsUseCase: SaveNumberOfRoundsUseCase,
    private val _saveUsernameUseCase: SaveUsernameUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<SettingsScreenState>(coroutineDispatcher) {
    private val _numberOfRounds: MutableStateFlow<Float> =
        MutableStateFlow(0f)

    private val _username: MutableStateFlow<String> =
        MutableStateFlow("")

    override val initialState: SettingsScreenState = SettingsScreenState.InitialState
    override val _state: Flow<SettingsScreenState> = combine(
        _numberOfRounds,
        _username,
    ) { numberOfRounds, username ->
        SettingsScreenState.ReadyState(
            numberOfRounds,
            username
        )
    }

    init {
        launch {
            _numberOfRounds.update {
                _getNumberOfRoundsUseCase(Unit).toFloat()
            }
            _username.update {
                _getUsernameUseCase(Unit)
            }
        }
    }

    fun changeLanguage(locale: String) {
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(locale))
    }

    fun changeUsername(username: String) = launch {
        _username.update { username }
        _saveUsernameUseCase(username)
    }

    fun onSliderChange(value: Float) {
        _numberOfRounds.update { value }
    }

    fun saveNumberOfRounds(value: Float) = launch {
        _saveNumberOfRoundsUseCase(value.toInt())
    }
}

sealed class SettingsScreenState {
    abstract val numberOfRounds: Float
    abstract val username: String

    data object InitialState : SettingsScreenState() {
        override val numberOfRounds: Float = 1f
        override val username: String = ""
    }

    data class ReadyState(
        override val numberOfRounds: Float,
        override val username: String
    ) : SettingsScreenState()
}