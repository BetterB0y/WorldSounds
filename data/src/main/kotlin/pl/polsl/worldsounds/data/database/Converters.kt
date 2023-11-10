package pl.polsl.worldsounds.data.database

import androidx.room.TypeConverter
import pl.polsl.worldsounds.domain.models.GameModeModel

internal class Converters {
    @TypeConverter
    fun fromGameMode(gameModeModel: GameModeModel): Int = gameModeModel.ordinal

    @TypeConverter
    fun toGameMode(gameMode: Int) = GameModeModel.values()[gameMode]
}