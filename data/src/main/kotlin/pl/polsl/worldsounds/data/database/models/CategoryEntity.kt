package pl.polsl.worldsounds.data.database.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "categories",
    indices = [Index(value = ["name"], unique = true)]
)
internal data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
) {
    companion object {
        fun new(
            name: String,
        ) = CategoryEntity(0, name)
    }
}
