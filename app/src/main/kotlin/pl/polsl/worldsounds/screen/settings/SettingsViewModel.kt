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
import pl.polsl.worldsounds.domain.usecases.GetAccelerometerThresholdUseCase
import pl.polsl.worldsounds.domain.usecases.GetNumberOfRoundsUseCase
import pl.polsl.worldsounds.domain.usecases.GetUsernameUseCase
import pl.polsl.worldsounds.domain.usecases.SaveAccelerometerThresholdUseCase
import pl.polsl.worldsounds.domain.usecases.SaveNumberOfRoundsUseCase
import pl.polsl.worldsounds.domain.usecases.SaveUsernameUseCase
import pl.polsl.worldsounds.models.Language
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val _getNumberOfRoundsUseCase: GetNumberOfRoundsUseCase,
    private val _getAccelerometerThresholdUseCase: GetAccelerometerThresholdUseCase,
    private val _getUsernameUseCase: GetUsernameUseCase,
    private val _saveNumberOfRoundsUseCase: SaveNumberOfRoundsUseCase,
    private val _saveAccelerometerThresholdUseCase: SaveAccelerometerThresholdUseCase,
    private val _saveUsernameUseCase: SaveUsernameUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<SettingsScreenState>(coroutineDispatcher) {
    private val _numberOfRounds: MutableStateFlow<Float> =
        MutableStateFlow(0f)

    private val _accelerometerThreshold: MutableStateFlow<Float> =
        MutableStateFlow(0f)

    private val _username: MutableStateFlow<String> =
        MutableStateFlow("")

    private val _language: MutableStateFlow<Language> =
        MutableStateFlow(Language.POLISH)


    override val initialState: SettingsScreenState = SettingsScreenState.InitialState
    override val _state: Flow<SettingsScreenState> = combine(
        _numberOfRounds,
        _accelerometerThreshold,
        _username,
        _language,
    ) { numberOfRounds, accelerometerThreshold, username, language ->
        SettingsScreenState.ReadyState(
            numberOfRounds,
            accelerometerThreshold,
            username,
            language,
        )
    }

    init {
        launch {
            _numberOfRounds.update {
                _getNumberOfRoundsUseCase(Unit).toFloat()
            }
            _accelerometerThreshold.update {
                _getAccelerometerThresholdUseCase(Unit)
            }
            _username.update {
                _getUsernameUseCase(Unit)
            }
            //todo get language
        }
    }

    fun changeLanguage(language: Language) {
        _language.update { language }
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(language.locale))
    }

    fun changeUsername(username: String) = launch {
        _username.update { username }
        _saveUsernameUseCase(username)
    }

    fun onRoundSliderChange(value: Float) {
        _numberOfRounds.update { value }
    }

    fun saveNumberOfRounds() = launch {
        _saveNumberOfRoundsUseCase(_numberOfRounds.value.toInt())
    }

    fun onAccelerometerSliderChange(value: Float) {
        _accelerometerThreshold.update { value }
    }

    fun saveAccelerometerThreshold() = launch {
        _saveAccelerometerThresholdUseCase(_accelerometerThreshold.value)
    }
}

sealed class SettingsScreenState {
    abstract val numberOfRounds: Float
    abstract val accelerometerThreshold: Float
    abstract val username: String
    abstract val language: Language

    data object InitialState : SettingsScreenState() {
        override val numberOfRounds: Float = 1f
        override val accelerometerThreshold: Float = 0f
        override val username: String = ""
        override val language: Language = Language.POLISH
    }

    data class ReadyState(
        override val numberOfRounds: Float,
        override val accelerometerThreshold: Float,
        override val username: String,
        override val language: Language,
    ) : SettingsScreenState()
}