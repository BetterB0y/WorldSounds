package pl.polsl.worldsounds.data.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import pl.polsl.worldsounds.domain.models.GameModeModel
import java.util.Locale

const val SETTINGS_NAME = "preferences"

internal interface Settings {
    suspend fun setGameMode(gameMode: GameModeModel)
    suspend fun getGameMode(): GameModeModel

    suspend fun setCategoryId(id: Long)
    suspend fun getCategoryId(): Long

    suspend fun setNumberOfRounds(value: Int)
    suspend fun getNumberOfRounds(): Int

    suspend fun setAccelerometerThreshold(value: Float)
    suspend fun getAccelerometerThreshold(): Float
    fun observeAccelerometerThreshold(): Flow<Float>

    suspend fun setUsername(value: String)
    suspend fun getUsername(): String

    suspend fun setAudioPath(value: String)
    suspend fun getAudioPath(): String

    suspend fun setLanguage(value: String)
    suspend fun getLanguage(): String
}

internal class SettingsImpl(private val _dataStore: DataStore<Preferences>) : Settings {
    companion object {
        private val GAME_MODE = intPreferencesKey("gameMode")
        private val CATEGORY = longPreferencesKey("category")
        private val NUMBER_OF_ROUNDS = intPreferencesKey("numberOfRounds")
        private val ACCELEROMETER_THRESHOLD = floatPreferencesKey("accelerometerThreshold")
        private val USERNAME = stringPreferencesKey("username")
        private val AUDIO_PATH = stringPreferencesKey("audioPath")
        private val LANGUAGE = stringPreferencesKey("language")
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

    override suspend fun setAccelerometerThreshold(value: Float) = _dataStore.set(
        ACCELEROMETER_THRESHOLD,
        value,
    )

    override suspend fun getAccelerometerThreshold(): Float {
        return _dataStore.get(ACCELEROMETER_THRESHOLD) ?: 1f
    }

    override fun observeAccelerometerThreshold(): Flow<Float> =
        _dataStore.data.distinctUntilChanged().map { it[ACCELEROMETER_THRESHOLD] ?: 1f }


    override suspend fun setUsername(value: String) = _dataStore.set(
        USERNAME,
        value,
    )

    override suspend fun getUsername(): String {
        return _dataStore.get(USERNAME) ?: "Player"
    }

    override suspend fun setAudioPath(value: String) = _dataStore.set(
        AUDIO_PATH,
        value,
    )

    override suspend fun getAudioPath(): String {
        return _dataStore.get(AUDIO_PATH) ?: ""
    }

    override suspend fun setLanguage(value: String) = _dataStore.set(
        LANGUAGE,
        value,
    )

    override suspend fun getLanguage(): String {
        return _dataStore.get(LANGUAGE) ?: Locale.getDefault().isO3Language
    }
}
