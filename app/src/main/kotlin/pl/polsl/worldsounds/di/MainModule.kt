package pl.polsl.worldsounds.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import pl.polsl.worldsounds.app.appFolder
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun providesCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO


    @Provides
    @Singleton
    fun providesAppFolder(): File = appFolder
}