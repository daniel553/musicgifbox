package com.tripletres.musicgifbox.util

import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.util.Duration

/**
 * Sound player controls playback through [MediaPlayer] for any clip.
 */
object SoundPlayer {
    private var player: MediaPlayer? = null
    private var sounds = mutableMapOf<String, Media>()

    /**
     * Plays a specific sound file.
     * @param path of resource
     * @param timeout in milliseconds to stop
     * @param callback
     */
    fun play(path: String, timeout: Long?, callback: PlayerCallback? = null) {
        if (player?.status == MediaPlayer.Status.PLAYING) {
            LogUtil.e(tag, "MediaPlayer is busy...")
            return
        }

        try {
            if (!sounds.containsKey(path)) {
                //TODO: resource by path way
                sounds[path] = Media(javaClass.getResource("../../../../$path").toString())
            }
            player = MediaPlayer(sounds[path])
            player?.setOnReady {
                callback?.onReady()
            }
            player?.setOnEndOfMedia {
                callback?.onStop()
                player?.stop()
            }

            timeout?.let {
                if (it > 0.0)
                    player?.stopTime = Duration(it.toDouble())
            }
            player?.play()
        } catch (ex: Exception) {
            LogUtil.e(tag, "sound can't be played $path", ex)
        }
    }

    /**
     * Stop current player execution
     */
    fun stop(callback: PlayerCallback?) {
        try {
            player?.stop()
            callback?.onStop()
        } catch (ex: Exception) {
            LogUtil.e(tag, "sound can't be stopped", ex)
        }
    }

    interface PlayerCallback {
        fun onReady()
        fun onStop()
    }
}