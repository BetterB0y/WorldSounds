package pl.polsl.worldsounds.models

sealed class RoundAssetsData {
    abstract val answerFileName: String

    data class OneSound(
        override val answerFileName: String,
        val audio: AudioData,
        val images: List<ImageData>
    ) : RoundAssetsData()

    data class OnePicture(
        override val answerFileName: String,
        val image: ImageData,
        val audios: List<AudioData>
    ) : RoundAssetsData()
}