package pl.polsl.worldsounds.data.database.models.views

import androidx.room.ColumnInfo
import androidx.room.DatabaseView

@DatabaseView(
    """
SELECT 
    c.id AS id,
    c.name AS name,
    COUNT(DISTINCT i.id) AS image_count,
    COUNT(DISTINCT a.id) AS audio_count
FROM 
    categories c
LEFT JOIN 
    Images i ON c.id = i.category_id
LEFT JOIN 
    Audios a ON c.id = a.category_id
GROUP BY 
    c.id, c.name;    
"""
)
internal data class CategoryCountView(
    val id: Long,
    val name: String,
    @ColumnInfo("image_count")
    val imageCount: Int,
    @ColumnInfo("audio_count")
    val audioCount: Int,
)