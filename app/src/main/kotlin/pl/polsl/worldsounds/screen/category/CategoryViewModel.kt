package pl.polsl.worldsounds.screen.category

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import pl.polsl.worldsounds.base.BaseViewModel
import pl.polsl.worldsounds.domain.usecases.GetGameModeUseCase
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val _getGameModeUseCase: GetGameModeUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<CategoryScreenState>(coroutineDispatcher) {
    override val initialState: CategoryScreenState = CategoryScreenState.InitialState
    override val _state: MutableStateFlow<CategoryScreenState> = MutableStateFlow(initialState)

    fun saveAndNavigate() = launch {
        val test = _getGameModeUseCase(Unit)
        Timber.e("Test $test")
    }
}

sealed class CategoryScreenState {
    data object InitialState : CategoryScreenState()
}