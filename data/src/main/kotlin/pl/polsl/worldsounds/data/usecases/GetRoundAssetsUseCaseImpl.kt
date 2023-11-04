package pl.polsl.worldsounds.data.usecases

import pl.polsl.worldsounds.data.repositories.GameRepository
import pl.polsl.worldsounds.domain.models.RoundAssetsModel
import pl.polsl.worldsounds.domain.usecases.GetRoundAssetsUseCase

internal class GetRoundAssetsUseCaseImpl(private val gameRepository: GameRepository) : GetRoundAssetsUseCase {
    override suspend fun invoke(input: GetRoundAssetsUseCase.Params): RoundAssetsModel {
        return gameRepository.getRoundAssets(input.categoryId, input.gameMode)
    }
}