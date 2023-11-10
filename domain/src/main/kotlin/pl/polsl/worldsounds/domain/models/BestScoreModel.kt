package pl.polsl.worldsounds.domain.models

data class BestScoreModel(
    val id: Long,
    val categoryName: String,
    val score: Int,
    val playerName: String
)
