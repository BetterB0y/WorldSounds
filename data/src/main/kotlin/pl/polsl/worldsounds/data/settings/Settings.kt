package pl.polsl.worldsounds.data.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import pl.polsl.worldsounds.domain.models.GameModeModel

const val SETTINGS_NAME = "preferences"

internal interface Settings {
    suspend fun setGameMode(gameMode: GameModeModel)
    suspend fun getGameMode(): GameModeModel
}

internal class SettingsImpl(private val _dataStore: DataStore<Preferences>) : Settings {
    companion object {
        private val GAME_MODE = intPreferencesKey("gameMode")
        private val CATEGORY = intPreferencesKey("category")
    }

    private suspend fun <T> DataStore<Preferences>.set(key: Preferences.Key<T>, value: T) {
        this.edit { it[key] = value }
    }

    private suspend fun <T> DataStore<Preferences>.get(key: Preferences.Key<T>): T? =
        this.data.map { it[key] }.first()

    override suspend fun setGameMode(gameMode: GameModeModel) = _dataStore.set(
        GAME_MODE,
        gameMode.ordinal
    )

    override suspend fun getGameMode(): GameModeModel {
        return GameModeModel.values()[_dataStore.get(GAME_MODE) ?: 0]
    }
}
