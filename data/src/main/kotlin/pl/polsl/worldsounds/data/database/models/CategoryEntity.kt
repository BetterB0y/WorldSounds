package pl.polsl.worldsounds.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "categories",
)
internal data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val path: String,
)
