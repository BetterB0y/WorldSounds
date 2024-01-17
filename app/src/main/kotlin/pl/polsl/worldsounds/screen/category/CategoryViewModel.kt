package pl.polsl.worldsounds.screen.category

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.polsl.worldsounds.base.BaseViewModel
import pl.polsl.worldsounds.base.Event
import pl.polsl.worldsounds.domain.models.GameModeModel
import pl.polsl.worldsounds.domain.usecases.GetGameModeUseCase
import pl.polsl.worldsounds.domain.usecases.GetNumberOfRoundsUseCase
import pl.polsl.worldsounds.domain.usecases.ObserveCategoriesUseCase
import pl.polsl.worldsounds.domain.usecases.SaveCategoryIdUseCase
import pl.polsl.worldsounds.models.CategoryData
import pl.polsl.worldsounds.models.mappers.toData
import pl.polsl.worldsounds.screen.destinations.OnePictureGameScreenDestination
import pl.polsl.worldsounds.screen.destinations.OneSoundGameScreenDestination
import javax.inject.Inject


@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val _saveCategoryIdUseCase: SaveCategoryIdUseCase,
    private val _getGameModeUseCase: GetGameModeUseCase,
    getNumberOfRoundsUseCase: GetNumberOfRoundsUseCase,
    observeCategoriesUseCase: ObserveCategoriesUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<CategoryScreenState>(coroutineDispatcher) {
    override val initialState: CategoryScreenState = CategoryScreenState.InitialState
    override val _state: Flow<CategoryScreenState> = observeCategoriesUseCase(Unit).map {
        CategoryScreenState.ReadyState(
            it.map { category -> category.toData() },
            getNumberOfRoundsUseCase(Unit)
        )
    }

    fun saveAndNavigate(category: CategoryData) = launch {
        _saveCategoryIdUseCase(category.id)
        sendEvent(
            when (_getGameModeUseCase(Unit)) {
                GameModeModel.OnePicture -> CategoryEvent.OpenOnePictureGame
                GameModeModel.OneSound -> CategoryEvent.OpenOneSoundGame
            }
        )
    }

    fun navigateBack() = launch {
        sendEvent(Event.Navigation.NavigateBack)
    }
}

sealed class CategoryEvent {
    object OpenOnePictureGame : Event.Navigation(OnePictureGameScreenDestination)
    object OpenOneSoundGame : Event.Navigation(OneSoundGameScreenDestination)
}

sealed class CategoryScreenState {
    abstract val categories: List<CategoryData>
    abstract val numberOfRounds: Int

    data object InitialState : CategoryScreenState() {
        override val categories: List<CategoryData> = emptyList()
        override val numberOfRounds: Int = 0
    }

    data class ReadyState(
        override val categories: List<CategoryData>,
        override val numberOfRounds: Int
    ) : CategoryScreenState()
}
