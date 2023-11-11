package pl.polsl.worldsounds.utils

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri

object AudioPlayer {
    private var mediaPlayer: MediaPlayer? = null

    val isAudioPlaying: Boolean get() = mediaPlayer?.isPlaying ?: false

    fun playAudio(context: Context, audio: Uri) {
        stopAudio()
        mediaPlayer = MediaPlayer.create(context, audio).apply {
            start()
        }
    }

    fun stopAudio() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}