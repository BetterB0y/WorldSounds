package pl.polsl.worldsounds.utils

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import pl.polsl.worldsounds.domain.usecases.GetAudioToPlayUseCase
import pl.polsl.worldsounds.domain.usecases.ObserveAccelerometerThresholdUseCase
import pl.polsl.worldsounds.screen.destinations.Destination
import pl.polsl.worldsounds.screen.destinations.OnePictureGameScreenDestination
import pl.polsl.worldsounds.screen.destinations.OneSoundGameScreenDestination
import pl.polsl.worldsounds.utils.AudioPlayer.isAudioPlaying
import kotlin.math.sqrt

class PlayAudioOnShakeListener(
    private val context: Context,
    private val _getAudioToPlayUseCase: GetAudioToPlayUseCase,
    private val _observeAccelerometerThresholdUseCase: ObserveAccelerometerThresholdUseCase,
) : SensorEventListener {
    private var currentDestination: Destination? = null
    private var acceleration = 10f
    private var currentAcceleration = SensorManager.GRAVITY_EARTH
    private var lastAcceleration = SensorManager.GRAVITY_EARTH
    private var threshold by mutableFloatStateOf(1f)

    private val isGameScreen: Boolean get() = currentDestination == OnePictureGameScreenDestination || currentDestination == OneSoundGameScreenDestination
    private val shouldPlayAudio: Boolean get() = acceleration > threshold && !isAudioPlaying && isGameScreen

    init {
        CoroutineScope(Dispatchers.IO).launch {
            _observeAccelerometerThresholdUseCase(Unit).collect {
                threshold = it
            }
        }
    }

    fun setCurrentDestination(currentDestination: Destination) {
        this.currentDestination = currentDestination
    }

    override fun onSensorChanged(event: SensorEvent) {
        val x = event.values[0]
        val y = event.values[1]
        val z = event.values[2]
        lastAcceleration = currentAcceleration
        currentAcceleration = sqrt((x * x + y * y + z * z).toDouble()).toFloat()

        val delta: Float = currentAcceleration - lastAcceleration
        acceleration = acceleration * 0.9f + delta

        if (shouldPlayAudio) {
            runBlocking {
                val audio = _getAudioToPlayUseCase(Unit)
                if (audio.isEmpty()) return@runBlocking
                AudioPlayer.playAudio(context, audio.toUri())
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
}