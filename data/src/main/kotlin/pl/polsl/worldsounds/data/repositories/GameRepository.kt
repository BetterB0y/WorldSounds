package pl.polsl.worldsounds.data.repositories

import pl.polsl.worldsounds.data.database.dao.ScoreDao
import pl.polsl.worldsounds.data.database.models.ScoreEntity
import pl.polsl.worldsounds.domain.models.GameModeModel
import pl.polsl.worldsounds.domain.models.RoundAssetsModel

internal class GameRepository(
    private val dao: ScoreDao,
    private val audioRepository: AudioRepository,
    private val imageRepository: ImageRepository
) {
    suspend fun getRoundAssets(categoryId: Long, numberOfRounds: Int, gameMode: GameModeModel): List<RoundAssetsModel> {
        return when (gameMode) {
            GameModeModel.OnePicture -> List(numberOfRounds) { getRoundForOnePicture(categoryId) }

            GameModeModel.OneSound -> List(numberOfRounds) { getRoundForOneSound(categoryId) }
        }
    }

    suspend fun saveScore(playerName: String, score: Int, categoryId: Long) {
        dao.insert(ScoreEntity.new(playerName, score, categoryId))
    }

    private suspend fun getRoundForOnePicture(categoryId: Long): RoundAssetsModel.OnePicture {
        val audios = audioRepository.getRandomAudios(categoryId)
        val correctAnswer = audios.random()
        val image = imageRepository.getAnswerImage(correctAnswer.name)
        return RoundAssetsModel.OnePicture(
            answerFileName = correctAnswer.name,
            audios = audios,
            image = image
        )
    }

    private suspend fun getRoundForOneSound(categoryId: Long): RoundAssetsModel.OneSound {
        val images = imageRepository.getRandomImages(categoryId)
        val correctAnswerName = images.random().name
        val audio = audioRepository.getAnswerAudio(correctAnswerName)
        return RoundAssetsModel.OneSound(
            answerFileName = correctAnswerName,
            images = images,
            audio = audio,
        )
    }
}