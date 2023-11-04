package pl.polsl.worldsounds.models

import java.io.File

data class ImageData(
    val id: Long,
    val file: File,
    val isHidden: Boolean = false,
)