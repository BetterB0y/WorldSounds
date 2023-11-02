package pl.polsl.worldsounds.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import pl.polsl.worldsounds.data.database.models.CategoryEntity

@Dao
internal interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: CategoryEntity): Long

    @Delete
    suspend fun delete(category: CategoryEntity)
}