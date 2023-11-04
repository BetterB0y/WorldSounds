package pl.polsl.worldsounds.domain.models

sealed class RoundAssetsModel {
    abstract val answerFileName: String

    data class OneSound(
        override val answerFileName: String,
        val audio: AudioModel,
        val images: List<ImageModel>
    ) : RoundAssetsModel()

    data class OnePicture(
        override val answerFileName: String,
        val image: ImageModel,
        val audios: List<AudioModel>
    ) : RoundAssetsModel()
}