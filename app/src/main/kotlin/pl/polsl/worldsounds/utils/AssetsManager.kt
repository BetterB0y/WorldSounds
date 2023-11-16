package pl.polsl.worldsounds.utils

import android.content.res.AssetManager
import pl.polsl.worldsounds.app.appFolder
import pl.polsl.worldsounds.domain.base.AssetsFolders
import java.io.File
import java.io.FileOutputStream

fun AssetManager.copyToAppFolder(assetsFolder: AssetsFolders) {
    list(assetsFolder.name)?.let { list ->
        val categoryFolder = File(appFolder, assetsFolder.name)
        categoryFolder.deleteRecursively()
        categoryFolder.mkdirs()

        list.forEach {
            open("${assetsFolder.name}/$it").use { input ->
                FileOutputStream(File(categoryFolder, it).absolutePath).use { output ->
                    input.copyTo(output)
                    output.flush()
                }
            }
        }
    }
}