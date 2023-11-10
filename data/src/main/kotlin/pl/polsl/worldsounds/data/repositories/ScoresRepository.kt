package pl.polsl.worldsounds.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import pl.polsl.worldsounds.data.database.dao.ScoreDao
import pl.polsl.worldsounds.data.database.models.ScoreEntity
import pl.polsl.worldsounds.domain.models.BestScoreModel
import pl.polsl.worldsounds.domain.models.GameModeModel

internal class ScoresRepository(
    private val dao: ScoreDao,
) {
    suspend fun saveScore(
        playerName: String,
        score: Int,
        gameModeModel: GameModeModel,
        categoryName: String
    ) {
        dao.insert(ScoreEntity.new(playerName, score, gameModeModel, categoryName))
    }

    fun observeScoresWithGameMode(
        gameModel: Flow<GameModeModel>,
    ): Flow<List<BestScoreModel>> = gameModel.flatMapLatest {
        dao.observeWith(it)
    }
}