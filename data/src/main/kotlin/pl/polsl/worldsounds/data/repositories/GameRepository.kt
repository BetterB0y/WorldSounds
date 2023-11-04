package pl.polsl.worldsounds.data.repositories

import pl.polsl.worldsounds.domain.models.GameModeModel
import pl.polsl.worldsounds.domain.models.RoundAssetsModel

internal class GameRepository(
    private val audioRepository: AudioRepository,
    private val imageRepository: ImageRepository
) {
    suspend fun getRoundAssets(categoryId: Long, gameMode: GameModeModel): RoundAssetsModel {
        return when (gameMode) {
            GameModeModel.OnePicture -> {
                val audios = audioRepository.getRandomAudios(categoryId)
                val correctAnswer = audios.random()
                val image = imageRepository.getAnswerImage(correctAnswer.name)
                RoundAssetsModel.OnePicture(
                    answerFileName = correctAnswer.name,
                    audios = audios,
                    image = image
                )
            }

            GameModeModel.OneSound -> {
                val images = imageRepository.getRandomImages(categoryId)
                val correctAnswerName = images.random().name
                val audio = audioRepository.getAnswerAudio(correctAnswerName)
                RoundAssetsModel.OneSound(
                    answerFileName = correctAnswerName,
                    images = images,
                    audio = audio,
                )
            }
        }
    }
}