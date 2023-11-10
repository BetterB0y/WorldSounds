package pl.polsl.worldsounds.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.polsl.worldsounds.data.database.dao.AudioDao
import pl.polsl.worldsounds.data.database.dao.CategoryDao
import pl.polsl.worldsounds.data.database.dao.ImageDao
import pl.polsl.worldsounds.data.database.dao.ScoreDao
import pl.polsl.worldsounds.data.database.models.AudioEntity
import pl.polsl.worldsounds.data.database.models.CategoryEntity
import pl.polsl.worldsounds.data.database.models.ImageEntity
import pl.polsl.worldsounds.data.database.models.ScoreEntity
import pl.polsl.worldsounds.data.database.models.views.CategoryCountView

@Database(
    entities = [
        CategoryEntity::class,
        ScoreEntity::class,
        AudioEntity::class,
        ImageEntity::class,
    ],
    views = [
        CategoryCountView::class,
    ],
    version = 1
)

@TypeConverters(Converters::class)
internal abstract class Database : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun audioDao(): AudioDao
    abstract fun imageDao(): ImageDao
    abstract fun scoreDao(): ScoreDao
}