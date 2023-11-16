@file:Suppress("FunctionName")

package pl.polsl.worldsounds.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import pl.polsl.worldsounds.data.database.models.CategoryEntity
import pl.polsl.worldsounds.data.database.models.views.CategoryCountView

@Dao
internal interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category: CategoryEntity): Long

    @Query("SELECT * FROM CategoryCountView ORDER BY name ASC")
    fun observeAll(): Flow<List<CategoryCountView>>

    @Query("SELECT * FROM CategoryCountView WHERE id = :id")
    suspend fun getById(id: Long): CategoryCountView

    @Transaction
    suspend fun deleteAll() {
        _deleteAll()
        _resetAutoIncrement()
    }

    @Query("DELETE FROM categories")
    suspend fun _deleteAll()

    @Query("DELETE FROM sqlite_sequence WHERE name='categories' OR name='audios' OR name='images'")
    suspend fun _resetAutoIncrement()
}