package pl.polsl.worldsounds

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.polsl.worldsounds.domain.usecases.GetAudioToPlayUseCase
import pl.polsl.worldsounds.domain.usecases.ScanFolderWithAssetsUseCase
import pl.polsl.worldsounds.screen.NavGraphs
import pl.polsl.worldsounds.screen.appCurrentDestinationAsState
import pl.polsl.worldsounds.screen.destinations.Destination
import pl.polsl.worldsounds.screen.startAppDestination
import pl.polsl.worldsounds.ui.resources.WorldSoundsTheme
import pl.polsl.worldsounds.utils.PlayAudioOnShakeListener
import java.util.Objects
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var scanFolderWithAssetsUseCase: ScanFolderWithAssetsUseCase

    @Inject
    lateinit var getAudioToPlayUseCase: GetAudioToPlayUseCase

    private lateinit var sensorManager: SensorManager
    private lateinit var sensorListener: PlayAudioOnShakeListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorListener = PlayAudioOnShakeListener(this, getAudioToPlayUseCase)
        setContent {
            WorldSoundsTheme {
                val navController = rememberNavController()
                val currentDestination: Destination =
                    navController.appCurrentDestinationAsState().value
                        ?: NavGraphs.root.startAppDestination
                sensorListener.setCurrentDestination(currentDestination)

                Scaffold { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        DestinationsNavHost(
                            navGraph = NavGraphs.root,
                            navController = navController,
                        )
                    }
                }
            }
        }

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        Objects.requireNonNull(sensorManager)
            .registerListener(
                sensorListener, sensorManager
                    .getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL
            )
    }


    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
            sensorListener, sensorManager.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER
            ), SensorManager.SENSOR_DELAY_NORMAL
        )
        CoroutineScope(Dispatchers.IO).launch {
            scanFolderWithAssetsUseCase(Unit)
        }
    }

    override fun onPause() {
        sensorManager.unregisterListener(sensorListener)
        super.onPause()
    }
}
