package pl.polsl.worldsounds.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "scores",
    indices = [Index("category_id")],
    foreignKeys = [ForeignKey(
        entity = CategoryEntity::class,
        parentColumns = ["id"],
        childColumns = ["category_id"],
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
internal data class ScoreEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "player_name")
    val playerName: String,
    val score: Int,
    @ColumnInfo(name = "category_id")
    val categoryId: Long,
) {
    companion object {
        fun new(
            playerName: String,
            score: Int,
            categoryId: Long,
        ) = ScoreEntity(0, playerName, score, categoryId)
    }
}
