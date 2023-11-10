package pl.polsl.worldsounds.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import pl.polsl.worldsounds.domain.models.GameModeModel

@Entity(
    tableName = "scores",
    indices = [Index("category_name"), Index("game_mode")],
)
internal data class ScoreEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "player_name")
    val playerName: String,
    val score: Int,
    @ColumnInfo(name = "game_mode")
    val gameMode: GameModeModel,
    @ColumnInfo(name = "category_name")
    val categoryName: String,
) {
    companion object {
        fun new(
            playerName: String,
            score: Int,
            gameMode: GameModeModel,
            categoryName: String,
        ) = ScoreEntity(0, playerName, score, gameMode, categoryName)
    }
}
