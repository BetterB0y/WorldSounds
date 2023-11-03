package pl.polsl.worldsounds.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.polsl.worldsounds.data.repositories.CategoryRepository
import pl.polsl.worldsounds.data.settings.Settings
import pl.polsl.worldsounds.data.usecases.GetGameModeUseCaseImpl
import pl.polsl.worldsounds.data.usecases.ObserveCategoriesUseCaseImpl
import pl.polsl.worldsounds.data.usecases.SaveGameModeUseCaseImpl
import pl.polsl.worldsounds.data.usecases.ScanFolderWithAssetsUseCaseImpl
import pl.polsl.worldsounds.domain.usecases.GetGameModeUseCase
import pl.polsl.worldsounds.domain.usecases.ObserveCategoriesUseCase
import pl.polsl.worldsounds.domain.usecases.SaveGameModeUseCase
import pl.polsl.worldsounds.domain.usecases.ScanFolderWithAssetsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object UseCaseModule {
    @Provides
    @Singleton
    fun providesSaveGameModeUseCase(settings: Settings): SaveGameModeUseCase =
        SaveGameModeUseCaseImpl(settings)

    @Provides
    @Singleton
    fun providesGetGameModeUseCase(settings: Settings): GetGameModeUseCase =
        GetGameModeUseCaseImpl(settings)

    @Provides
    @Singleton
    fun providesScanFolderWithAssetsUseCase(categoryRepository: CategoryRepository): ScanFolderWithAssetsUseCase =
        ScanFolderWithAssetsUseCaseImpl(categoryRepository)

    @Provides
    @Singleton
    fun providesObserveCategoriesUseCase(categoryRepository: CategoryRepository): ObserveCategoriesUseCase =
        ObserveCategoriesUseCaseImpl(categoryRepository)
}