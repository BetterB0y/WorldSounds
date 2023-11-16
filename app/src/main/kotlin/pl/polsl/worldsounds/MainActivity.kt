package pl.polsl.worldsounds

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Scaffold
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.polsl.worldsounds.domain.usecases.GetAudioToPlayUseCase
import pl.polsl.worldsounds.domain.usecases.ObserveAccelerometerThresholdUseCase
import pl.polsl.worldsounds.domain.usecases.ScanFolderWithAssetsUseCase
import pl.polsl.worldsounds.screen.NavGraphs
import pl.polsl.worldsounds.screen.appCurrentDestinationAsState
import pl.polsl.worldsounds.screen.destinations.Destination
import pl.polsl.worldsounds.screen.startAppDestination
import pl.polsl.worldsounds.ui.components.GlobalBackground
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

    @Inject
    lateinit var observeAccelerometerThresholdUseCase: ObserveAccelerometerThresholdUseCase

    private lateinit var sensorManager: SensorManager
    private lateinit var sensorListener: PlayAudioOnShakeListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemUI()
        sensorListener = PlayAudioOnShakeListener(this, getAudioToPlayUseCase, observeAccelerometerThresholdUseCase)
        setContent {
            WorldSoundsTheme {
                val navController = rememberNavController()
                val currentDestination: Destination =
                    navController.appCurrentDestinationAsState().value
                        ?: NavGraphs.root.startAppDestination
                sensorListener.setCurrentDestination(currentDestination)

                Scaffold { innerPadding ->
                    GlobalBackground(innerPadding) {
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

    private fun hideSystemUI() {
        actionBar?.hide()

        WindowCompat.setDecorFitsSystemWindows(window, false)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            @Suppress("DEPRECATION")
            //TODO Check on older android
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        } else {
            window.insetsController?.apply {
                hide(WindowInsets.Type.statusBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
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
