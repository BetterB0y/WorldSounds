package pl.polsl.worldsounds.models

import java.io.File

sealed class RoundAssetsData {
    abstract val answerFileName: String

    data class OneSound(
        override val answerFileName: String,
        val audio: AudioData,
        val images: List<ImageData>
    ) : RoundAssetsData() {
        companion object {
            fun default() = OneSound("", AudioData(id = 0, file = File("")), emptyList())
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
        override val answerFileName: String,
        val image: ImageData,
        val audios: List<AudioData>
    ) : RoundAssetsData() {
        companion object {
            fun default() =
                OnePicture("", ImageData(id = 0, file = File("")), emptyList())
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