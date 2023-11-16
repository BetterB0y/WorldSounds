package pl.polsl.worldsounds.app

import android.app.Application
import android.os.Environment
import dagger.hilt.android.HiltAndroidApp
import pl.polsl.worldsounds.BuildConfig
import pl.polsl.worldsounds.domain.base.Config
import timber.log.Timber
import java.io.File

val appFolder: File
    get() = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), Config.appFolder)

val basePath: String get() = appFolder.absolutePath

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