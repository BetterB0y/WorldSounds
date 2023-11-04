package pl.polsl.worldsounds.domain.models

data class AudioModel(
    val id: Long,
    val name: String,
    val extension: String
) {
    val fileName: String
        get() = "$name.$extension"

}