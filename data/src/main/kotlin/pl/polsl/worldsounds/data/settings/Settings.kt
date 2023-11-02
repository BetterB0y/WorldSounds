package pl.polsl.worldsounds.data.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey

const val SETTINGS_NAME = "preferences"

internal interface Settings

internal class SettingsImpl(private val _dataStore: DataStore<Preferences>) : Settings {
    companion object {
        private val GAME_MODE = intPreferencesKey("gameMode")
        private val CATEGORY = intPreferencesKey("category")
    }
}
