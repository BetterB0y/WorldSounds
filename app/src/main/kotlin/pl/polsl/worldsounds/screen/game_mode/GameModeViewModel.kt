package pl.polsl.worldsounds.screen.game_mode

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import pl.polsl.worldsounds.base.BaseViewModel
import pl.polsl.worldsounds.base.Event
import pl.polsl.worldsounds.data.database.dao.AudioDao
import pl.polsl.worldsounds.data.database.dao.CategoryDao
import pl.polsl.worldsounds.data.database.dao.ImageDao
import pl.polsl.worldsounds.data.database.models.AudioEntity
import pl.polsl.worldsounds.data.database.models.CategoryEntity
import pl.polsl.worldsounds.data.database.models.ImageEntity
import pl.polsl.worldsounds.data.models.GameModeUi
import pl.polsl.worldsounds.data.models.mappers.toModel
import pl.polsl.worldsounds.domain.usecase.SaveGameModeUseCase
import pl.polsl.worldsounds.screen.destinations.CategoryScreenDestination
import timber.log.Timber
import java.io.File
import javax.inject.Inject


@HiltViewModel
class GameModeViewModel @Inject constructor(
    private val _saveGameModeUseCase: SaveGameModeUseCase,
    private val categoryDao: CategoryDao,
    private val audioDao: AudioDao,
    private val imageDao: ImageDao,
    coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<GameModeScreenState>(coroutineDispatcher) {
    override val initialState: GameModeScreenState = GameModeScreenState.InitialState
    override val _state: MutableStateFlow<GameModeScreenState> = MutableStateFlow(initialState)

    fun saveAndNavigate(gameMode: GameModeUi) = launch {
        _saveGameModeUseCase(gameMode.toModel())
        sendEvent(GameModeEvent.OpenCategoryScreen)
    }

    val folderPath = "/storage/emulated/0/WorldSounds"
    val folder = File(folderPath)

    fun scanWorldSounds() = launch {
        folder.listFiles()?.forEach { categoryDir ->
            if (categoryDir.isDirectory) {
                Timber.e("Category name: ${categoryDir.name}")
                val categoryId = categoryDao.insert(CategoryEntity(0, categoryDir.name))

                val filesList = categoryDir.listFiles { file ->
                    file.extension == "mp3" || file.extension == "jpg"
                }

                filesList?.forEach { file ->
                    if (file.name.endsWith(".mp3")) {
                        audioDao.insert(AudioEntity(0, file.name, categoryId))
                    } else {
                        imageDao.insert(ImageEntity(0, file.name, categoryId))
                    }
                }
            }
        }
    }
}

sealed class GameModeEvent {
    object OpenCategoryScreen : Event.Navigation(CategoryScreenDestination)
}

sealed class GameModeScreenState {
    data object InitialState : GameModeScreenState()
}