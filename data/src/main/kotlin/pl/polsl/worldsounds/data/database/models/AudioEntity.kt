package pl.polsl.worldsounds.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "audios",
    indices = [
        Index("category_id"),
        Index(value = ["name", "category_id"], unique = true)
    ],
    foreignKeys = [ForeignKey(
        entity = CategoryEntity::class,
        parentColumns = ["id"],
        childColumns = ["category_id"],
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
internal data class AudioEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    @ColumnInfo(name = "category_id")
    val categoryId: Long
) {
    companion object {
        fun new(
            name: String,
            categoryId: Long,
        ) = AudioEntity(0, name, categoryId)
    }
}