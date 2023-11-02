package pl.polsl.worldsounds.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.polsl.worldsounds.data.database.Database
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "worldsounds_db"
        ).build()
    }

    @Singleton
    @Provides
    fun providesCategoryDao(database: Database) = database.categoryDao()

    @Singleton
    @Provides
    fun providesAudioDao(database: Database) = database.audioDao()

    @Singleton
    @Provides
    fun providesImageDao(database: Database) = database.imageDao()

    @Singleton
    @Provides
    fun providesScoreDao(database: Database) = database.scoreDao()
}