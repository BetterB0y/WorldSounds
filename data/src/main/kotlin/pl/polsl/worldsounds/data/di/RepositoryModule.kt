package pl.polsl.worldsounds.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.polsl.worldsounds.data.database.dao.AudioDao
import pl.polsl.worldsounds.data.database.dao.CategoryDao
import pl.polsl.worldsounds.data.database.dao.ImageDao
import pl.polsl.worldsounds.data.database.dao.ScoreDao
import pl.polsl.worldsounds.data.repositories.AudioRepository
import pl.polsl.worldsounds.data.repositories.CategoryRepository
import pl.polsl.worldsounds.data.repositories.GameRepository
import pl.polsl.worldsounds.data.repositories.ImageRepository
import pl.polsl.worldsounds.data.repositories.ScoresRepository
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {

    @Singleton
    @Provides
    fun provideCategoryRepository(
        appFolder: File,
        dao: CategoryDao,
        audioRepository: AudioRepository,
        imageRepository: ImageRepository
    ): CategoryRepository =
        CategoryRepository(
            appFolder,
            dao,
            audioRepository,
            imageRepository,
        )

    @Singleton
    @Provides
    fun provideAudioRepository(
        dao: AudioDao,
    ): AudioRepository =
        AudioRepository(
            dao,
        )

    @Singleton
    @Provides
    fun provideImageRepository(
        dao: ImageDao,
    ): ImageRepository =
        ImageRepository(
            dao,
        )

    @Singleton
    @Provides
    fun provideGameRepository(
        audioRepository: AudioRepository,
        imageRepository: ImageRepository
    ): GameRepository =
        GameRepository(
            audioRepository,
            imageRepository,
        )

    @Singleton
    @Provides
    fun provideScoresRepository(
        dao: ScoreDao,
    ): ScoresRepository =
        ScoresRepository(
            dao,
        )
}