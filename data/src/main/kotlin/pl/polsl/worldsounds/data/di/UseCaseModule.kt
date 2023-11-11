package pl.polsl.worldsounds.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.polsl.worldsounds.data.repositories.CategoryRepository
import pl.polsl.worldsounds.data.repositories.GameRepository
import pl.polsl.worldsounds.data.repositories.ScoresRepository
import pl.polsl.worldsounds.data.settings.Settings
import pl.polsl.worldsounds.data.usecases.GetAudioToPlayUseCaseImpl
import pl.polsl.worldsounds.data.usecases.GetCategoryUseCaseImpl
import pl.polsl.worldsounds.data.usecases.GetGameModeUseCaseImpl
import pl.polsl.worldsounds.data.usecases.GetNumberOfRoundsUseCaseImpl
import pl.polsl.worldsounds.data.usecases.GetRoundsAssetsUseCaseImpl
import pl.polsl.worldsounds.data.usecases.GetUsernameUseCaseImpl
import pl.polsl.worldsounds.data.usecases.ObserveCategoriesUseCaseImpl
import pl.polsl.worldsounds.data.usecases.ObserveScoresWithGameModeUseCaseImpl
import pl.polsl.worldsounds.data.usecases.SaveAudioToPlayUseCaseImpl
import pl.polsl.worldsounds.data.usecases.SaveCategoryIdUseCaseImpl
import pl.polsl.worldsounds.data.usecases.SaveGameModeUseCaseImpl
import pl.polsl.worldsounds.data.usecases.SaveNumberOfRoundsUseCaseImpl
import pl.polsl.worldsounds.data.usecases.SaveScoreUseCaseImpl
import pl.polsl.worldsounds.data.usecases.SaveUsernameUseCaseImpl
import pl.polsl.worldsounds.data.usecases.ScanFolderWithAssetsUseCaseImpl
import pl.polsl.worldsounds.domain.usecases.GetAudioToPlayUseCase
import pl.polsl.worldsounds.domain.usecases.GetCategoryUseCase
import pl.polsl.worldsounds.domain.usecases.GetGameModeUseCase
import pl.polsl.worldsounds.domain.usecases.GetNumberOfRoundsUseCase
import pl.polsl.worldsounds.domain.usecases.GetRoundsAssetsUseCase
import pl.polsl.worldsounds.domain.usecases.GetUsernameUseCase
import pl.polsl.worldsounds.domain.usecases.ObserveCategoriesUseCase
import pl.polsl.worldsounds.domain.usecases.ObserveScoresWithGameModeUseCase
import pl.polsl.worldsounds.domain.usecases.SaveAudioToPlayUseCase
import pl.polsl.worldsounds.domain.usecases.SaveCategoryIdUseCase
import pl.polsl.worldsounds.domain.usecases.SaveGameModeUseCase
import pl.polsl.worldsounds.domain.usecases.SaveNumberOfRoundsUseCase
import pl.polsl.worldsounds.domain.usecases.SaveScoreUseCase
import pl.polsl.worldsounds.domain.usecases.SaveUsernameUseCase
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

    @Provides
    @Singleton
    fun providesSaveCategoryIdUseCase(settings: Settings): SaveCategoryIdUseCase =
        SaveCategoryIdUseCaseImpl(settings)

    @Provides
    @Singleton
    fun providesGetCategoryIdUseCase(
        settings: Settings,
        categoryRepository: CategoryRepository
    ): GetCategoryUseCase =
        GetCategoryUseCaseImpl(settings, categoryRepository)

    @Provides
    @Singleton
    fun providesGetRoundsAssetsUseCase(gameRepository: GameRepository): GetRoundsAssetsUseCase =
        GetRoundsAssetsUseCaseImpl(gameRepository)

    @Provides
    @Singleton
    fun providesGetNumberOfRoundsUseCase(settings: Settings): GetNumberOfRoundsUseCase =
        GetNumberOfRoundsUseCaseImpl(settings)

    @Provides
    @Singleton
    fun providesSaveNumberOfRoundsUseCase(settings: Settings): SaveNumberOfRoundsUseCase =
        SaveNumberOfRoundsUseCaseImpl(settings)

    @Provides
    @Singleton
    fun providesGetUsernameUseCase(settings: Settings): GetUsernameUseCase =
        GetUsernameUseCaseImpl(settings)

    @Provides
    @Singleton
    fun providesSaveUsernameUseCase(settings: Settings): SaveUsernameUseCase =
        SaveUsernameUseCaseImpl(settings)

    @Provides
    @Singleton
    fun providesSaveScoreUseCase(settings: Settings, scoresRepository: ScoresRepository): SaveScoreUseCase =
        SaveScoreUseCaseImpl(settings, scoresRepository)

    @Provides
    @Singleton
    fun providesObserveScoresWithGameModeUseCase(scoresRepository: ScoresRepository): ObserveScoresWithGameModeUseCase =
        ObserveScoresWithGameModeUseCaseImpl(scoresRepository)

    @Provides
    @Singleton
    fun providesGetAudioToPlayUseCase(settings: Settings): GetAudioToPlayUseCase =
        GetAudioToPlayUseCaseImpl(settings)

    @Provides
    @Singleton
    fun providesSaveAudioToPlayUseCase(settings: Settings): SaveAudioToPlayUseCase =
        SaveAudioToPlayUseCaseImpl(settings)

}