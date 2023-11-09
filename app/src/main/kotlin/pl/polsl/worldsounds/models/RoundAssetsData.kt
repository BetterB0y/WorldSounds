package pl.polsl.worldsounds.models

import java.io.File

sealed class RoundAssetsData {
    abstract val answerFileName: String

    data class OneSound(
        val audio: AudioData,
        val images: List<ImageData>
    ) : RoundAssetsData() {
        override val answerFileName: String get() = audio.file.nameWithoutExtension

        companion object {
            fun default() = OneSound(AudioData(id = 0, file = File("")), emptyList())
        }

        fun hideImage(fileName: String): OneSound {
            return copy(images = images.map {
                if (it.file.nameWithoutExtension == fileName) it.copy(
                    isHidden = true
                ) else it
            })
        }
    }

    data class OnePicture(
        val image: ImageData,
        val audios: List<AudioData>
    ) : RoundAssetsData() {
        override val answerFileName: String get() = image.file.nameWithoutExtension
        companion object {
            fun default() =
                OnePicture(ImageData(id = 0, file = File("")), emptyList())
        }

        fun hideAudio(fileName: String): OnePicture {
            return copy(audios = audios.map {
                if (it.file.nameWithoutExtension == fileName) it.copy(
                    isHidden = true
                ) else it
            })
        }
    }
}