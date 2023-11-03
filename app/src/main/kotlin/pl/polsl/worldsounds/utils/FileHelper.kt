package pl.polsl.worldsounds.utils

import pl.polsl.worldsounds.domain.base.Config
import java.io.File


object FileHelper {
    fun buildFile(category: String, file: String): File {
        return File(buildPath(category, file))
    }

    private fun buildPath(category: String, file: String): String {
        return StringBuilder().apply {
            append(Config.basePath)
            append(category)
            append("/")
            append(file)
        }.toString()
    }
}