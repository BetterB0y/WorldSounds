package pl.polsl.worldsounds.data.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import pl.polsl.worldsounds.domain.models.GameModeModel

const val SETTINGS_NAME = "preferences"

internal interface Settings {
    suspend fun setGameMode(gameMode: GameModeModel)
    suspend fun getGameMode(): GameModeModel

    suspend fun setCategoryId(id: Long)
    suspend fun getCategoryId(): Long

    suspend fun setNumberOfRounds(value: Int)
    suspend fun getNumberOfRounds(): Int

    suspend fun setUsername(value: String)
    suspend fun getUsername(): String
}

internal class SettingsImpl(private val _dataStore: DataStore<Preferences>) : Settings {
    companion object {
        private val GAME_MODE = intPreferencesKey("gameMode")
        private val CATEGORY = longPreferencesKey("category")
        private val NUMBER_OF_ROUNDS = intPreferencesKey("numberOfRounds")
        private val USERNAME = stringPreferencesKey("username")
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

    override suspend fun setCategoryId(id: Long) = _dataStore.set(
        CATEGORY,
        id,
    )

    override suspend fun getCategoryId(): Long {
        return _dataStore.get(CATEGORY) ?: 0L
    }

    override suspend fun setNumberOfRounds(value: Int) = _dataStore.set(
        NUMBER_OF_ROUNDS,
        value,
    )

    override suspend fun getNumberOfRounds(): Int {
        return _dataStore.get(NUMBER_OF_ROUNDS) ?: 1
    }

    override suspend fun setUsername(value: String) = _dataStore.set(
        USERNAME,
        value,
    )

    override suspend fun getUsername(): String {
        return _dataStore.get(USERNAME) ?: ""
    }
}
