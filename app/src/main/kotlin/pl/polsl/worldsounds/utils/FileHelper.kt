package pl.polsl.worldsounds.utils

import pl.polsl.worldsounds.app.appFolder
import java.io.File


object FileHelper {
    fun buildFile(category: String, file: String): File {
        return File(appFolder, "$category/$file")
    }
}