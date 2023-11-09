package pl.polsl.worldsounds.domain.models

sealed class RoundAssetsModel {
    abstract val answerId: Long
    abstract val answerFileName: String

    data class OneSound(
        val audio: AudioModel,
        val images: List<ImageModel>
    ) : RoundAssetsModel() {
        override val answerId: Long = audio.id
        override val answerFileName: String get() = audio.fileName
    }

    data class OnePicture(
        val image: ImageModel,
        val audios: List<AudioModel>
    ) : RoundAssetsModel() {
        override val answerId: Long = image.id
        override val answerFileName: String get() = image.fileName
    }
}