package pl.polsl.worldsounds.utils

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import androidx.annotation.RawRes

object AudioPlayer {
    private var mediaPlayer: MediaPlayer? = null

    val isAudioPlaying: Boolean
        get() =
            try {
                mediaPlayer?.isPlaying ?: false
            } catch (e: Exception) {
                false
            }

    fun playAudio(context: Context, audio: Uri) {
        stopAudio()
        mediaPlayer = MediaPlayer.create(context, audio).apply {
            start()
        }
    }

    fun playAudio(context: Context, @RawRes audio: Int) {
        stopAudio()
        mediaPlayer = MediaPlayer.create(context, audio).apply {
            start()
        }
    }

    fun stopAudio() {
        if (isAudioPlaying) {
            mediaPlayer?.stop()
        }
        mediaPlayer?.release()
        mediaPlayer = null
    }
}