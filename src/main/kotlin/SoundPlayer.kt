import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer

/**
 * Sound player controls playback through [MediaPlayer] for any clip.
 */
object SoundPlayer {
    private var player: MediaPlayer?=null

    /**
     * Plays a specific sound file.
     */
    fun play(path: String) {
        try {
            val sound = Media(SoundPlayer::class.java.getResource(path)?.toString());
            player = MediaPlayer(sound)
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
}