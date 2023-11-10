package pl.polsl.worldsounds.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pl.polsl.worldsounds.data.database.models.ScoreEntity
import pl.polsl.worldsounds.domain.models.BestScoreModel
import pl.polsl.worldsounds.domain.models.GameModeModel

@Dao
internal interface ScoreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(score: ScoreEntity): Long

    @Query(
        """
        SELECT s.id, c.name as categoryName, MAX(s.score) as score, s.player_name as playerName 
        FROM scores AS s
        INNER JOIN categories AS c ON s.category_name = c.name
        WHERE s.game_mode = :gameMode
        GROUP BY c.name
        ORDER BY score DESC
    """
    )
    fun observeWith(gameMode: GameModeModel): Flow<List<BestScoreModel>>
}