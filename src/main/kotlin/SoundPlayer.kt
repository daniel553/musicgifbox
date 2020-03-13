import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer

/**
 * Sound player controls playback through [MediaPlayer] for any clip.
 */
object SoundPlayer {
    private var player: MediaPlayer?=null
    private var sounds = mutableMapOf<String, Media>()

    /**
     * Plays a specific sound file.
     * @param path of resource
     * @param loop to repeat until stop
     * @param callback
     */
    fun play(path: String, loop: Boolean? = false, callback: PlayerCallback? = null) {
        try {
            if(!sounds.containsKey(path))
                sounds[path] = Media(MyApp::class.java.getResource(path)?.toString())

            player = MediaPlayer(sounds[path])
            player?.setOnReady {
                //println("On Ready")
                callback?.onReady()
            }
            player?.play()
        } catch (ex: Exception) {
            println("sound can't be played $path")
        }
    }

    /**
     * Stop current player execution
     */
    fun stop(){
        try{
            player?.stop()
        }catch (ex: Exception){
            println("sound can't be stopped")
        }
    }

    interface PlayerCallback {
        fun onReady()
    }
}