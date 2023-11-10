package pl.polsl.worldsounds.models.mappers

import pl.polsl.worldsounds.domain.models.BestScoreModel
import pl.polsl.worldsounds.models.BestScoreData


fun BestScoreModel.toData(): BestScoreData = BestScoreData(
    id = id,
    categoryName = categoryName,
    score = score,
    playerName = playerName
)