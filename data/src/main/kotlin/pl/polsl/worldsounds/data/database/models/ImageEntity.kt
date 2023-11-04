package pl.polsl.worldsounds.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "images",
    indices = [
        Index("category_id"),
        Index(value = ["name", "extension", "category_id"], unique = true)
    ],
    foreignKeys = [ForeignKey(
        entity = CategoryEntity::class,
        parentColumns = ["id"],
        childColumns = ["category_id"],
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
internal data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val extension: String,
    @ColumnInfo(name = "category_id")
    val categoryId: Long
) {
    val fileName: String
        get() = "$name.$extension"

    companion object {
        fun new(
            name: String,
            extension: String,
            categoryId: Long,
        ) = ImageEntity(0, name, extension, categoryId)
    }
}