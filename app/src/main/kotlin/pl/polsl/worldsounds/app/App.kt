package pl.polsl.worldsounds.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import pl.polsl.worldsounds.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(MethodNameDebugTree())
        }
    }

}

class MethodNameDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return "(${element.fileName}:${element.lineNumber})#${element.methodName}"
    }
}