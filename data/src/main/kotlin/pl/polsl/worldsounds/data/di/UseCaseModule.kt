package pl.polsl.worldsounds.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.polsl.worldsounds.data.settings.Settings
import pl.polsl.worldsounds.data.usecase.GetGameModeUseCaseImpl
import pl.polsl.worldsounds.data.usecase.SaveGameModeUseCaseImpl
import pl.polsl.worldsounds.domain.usecase.GetGameModeUseCase
import pl.polsl.worldsounds.domain.usecase.SaveGameModeUseCase
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
}