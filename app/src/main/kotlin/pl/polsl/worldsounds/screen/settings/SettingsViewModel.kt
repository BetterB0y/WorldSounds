package pl.polsl.worldsounds.screen.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import pl.polsl.worldsounds.base.BaseViewModel
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
    coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<SettingsScreenState>(coroutineDispatcher) {
    override val initialState: SettingsScreenState = SettingsScreenState.InitialState
    override val _state: MutableStateFlow<SettingsScreenState> = MutableStateFlow(initialState)

    fun changeLanguage(locale: String) {
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(locale))
    }
}

sealed class SettingsScreenState {
    data object InitialState : SettingsScreenState()
}