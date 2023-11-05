package pl.polsl.worldsounds.data.usecases

import pl.polsl.worldsounds.data.repositories.GameRepository
import pl.polsl.worldsounds.domain.models.RoundAssetsModel
import pl.polsl.worldsounds.domain.usecases.GetRoundsAssetsUseCase

internal class GetRoundsAssetsUseCaseImpl(private val gameRepository: GameRepository) : GetRoundsAssetsUseCase {
    override suspend fun invoke(input: GetRoundsAssetsUseCase.Params): List<RoundAssetsModel> {
        return gameRepository.getRoundAssets(input.categoryId, input.numberOfRounds, input.gameMode)
    }
}