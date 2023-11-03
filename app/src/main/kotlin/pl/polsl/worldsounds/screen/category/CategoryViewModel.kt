package pl.polsl.worldsounds.screen.category

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.polsl.worldsounds.base.BaseViewModel
import pl.polsl.worldsounds.domain.usecases.GetGameModeUseCase
import pl.polsl.worldsounds.domain.usecases.ObserveCategoriesUseCase
import pl.polsl.worldsounds.models.CategoryData
import pl.polsl.worldsounds.models.mappers.toData
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val _getGameModeUseCase: GetGameModeUseCase,
    observeCategoriesUseCase: ObserveCategoriesUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<CategoryScreenState>(coroutineDispatcher) {
    override val initialState: CategoryScreenState = CategoryScreenState.InitialState
    override val _state: Flow<CategoryScreenState> = observeCategoriesUseCase(Unit).map {
        CategoryScreenState.ReadyState(it.map { category -> category.toData() })
    }

    fun saveAndNavigate() = launch {
        val test = _getGameModeUseCase(Unit)
        Timber.e("Test $test")
    }
}

sealed class CategoryScreenState {
    abstract val categories: List<CategoryData>

    data object InitialState : CategoryScreenState() {
        override val categories: List<CategoryData> = emptyList()
    }

    data class ReadyState(
        override val categories: List<CategoryData> = emptyList()
    ) : CategoryScreenState()
}
