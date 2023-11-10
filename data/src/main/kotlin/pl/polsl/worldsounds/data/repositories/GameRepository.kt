package pl.polsl.worldsounds.data.repositories

import pl.polsl.worldsounds.domain.models.GameModeModel
import pl.polsl.worldsounds.domain.models.RoundAssetsModel

internal class GameRepository(
    private val audioRepository: AudioRepository,
    private val imageRepository: ImageRepository
) {
    suspend fun getRoundAssets(categoryId: Long, numberOfRounds: Int, gameMode: GameModeModel): List<RoundAssetsModel> {
        val roundAssets: MutableList<RoundAssetsModel> = mutableListOf()
        val answers: MutableSet<Long> = mutableSetOf()

        val getAsset: suspend (Long, Set<Long>) -> RoundAssetsModel = when (gameMode) {
            GameModeModel.OnePicture -> ::getRoundForOnePicture
            GameModeModel.OneSound -> ::getRoundForOneSound
        }

        for (i in 0 until numberOfRounds) {
            val assets = getAsset(categoryId, answers)
            roundAssets.add(assets)
            answers.add(assets.answerId)
        }
        return roundAssets
    }

    private suspend fun getRoundForOnePicture(categoryId: Long, answers: Set<Long>): RoundAssetsModel.OnePicture {
        val image = imageRepository.getRandomAnswerNotIn(categoryId, answers)
        val audios = audioRepository.getAudioAssets(categoryId, image.name)
        return RoundAssetsModel.OnePicture(
            audios = audios,
            image = image
        )
    }

    private suspend fun getRoundForOneSound(categoryId: Long, answers: Set<Long>): RoundAssetsModel.OneSound {
        val audio = audioRepository.getRandomAnswerNotIn(categoryId, answers)
        val images = imageRepository.getImageAssets(categoryId, audio.name)
        return RoundAssetsModel.OneSound(
            images = images,
            audio = audio,
        )
    }
}