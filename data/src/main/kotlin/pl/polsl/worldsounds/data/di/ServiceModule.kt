package pl.polsl.worldsounds.data.di

import android.content.Context
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import pl.polsl.worldsounds.data.settings.SETTINGS_NAME
import pl.polsl.worldsounds.data.settings.Settings
import pl.polsl.worldsounds.data.settings.SettingsImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {
    @Singleton
    @Provides
    fun providesSettings(@ApplicationContext appContext: Context): Settings = SettingsImpl(
        PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(
                SharedPreferencesMigration(
                    appContext,
                    SETTINGS_NAME
                )
            ),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { appContext.preferencesDataStoreFile(SETTINGS_NAME) },
        ),
    )
}