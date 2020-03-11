import javafx.scene.image.Image
import javafx.scene.image.ImageView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jnativehook.keyboard.NativeKeyEvent
import org.jnativehook.keyboard.NativeKeyListener
import tornadofx.View
import tornadofx.imageview
import tornadofx.toProperty
import tornadofx.vbox

/**
 * Main and unique view to show the Image, Gif and play clips.
 */
class MyView : View(), NativeKeyListener {
    /**
     * Member image view that is used to present Images or Gifs
     */
    private var mainImageView: ImageView? = null
    /**
     * Default image path to show the "background element in the window.
     */
    private val defaultImagePath = "petepandalogo.png"

    /**
     * Collection of clips [Clip] to be loaded and presented in screen.
     */
    private val clips: Array<Clip> = arrayOf(
        Clip(11, "download.gif", "0.mp3", 3000) // Test
    )

    /**
     * Root view, shows a default image at the beginning of times
     */
    override val root = vbox {
        val url = defaultImagePath
        mainImageView = imageview(url.toString()) {
            setPrefSize(200.0, 200.0)
            fitHeightProperty().bind(parent.prefHeight(200.0).toProperty())
            fitWidthProperty().bind(parent.prefWidth(200.0).toProperty())
        }
    }

    init {
        KeyListener.addNativeKeyListener(this)
    }

    /**
     * Presents an animation accoding to a code key
     * @param keyCode - positive integer of native key pressed
     */
    private fun showAnimation(keyCode: Int) {
        //Keycode must match with Clip keycode
        val clip = clips.find { it.keyCode == keyCode }

        if (clip != null) {
            replaceImage(clip.imageOfGif)
            if(clip.timeout > 0){
                //Create a time out.
                GlobalScope.launch {
                    delay(clip.timeout)
                    //Reset default image
                    replaceImage(defaultImagePath)
                    stopCurrentSound()
                }
            }
            playSound(clip.sound)
        } else {
            println("Cannot play clip")
        }
    }

    /**
     * Replaces the main image view with parameter
     * @param url - a new image path from resources
     */
    private fun replaceImage(url: String) {
        mainImageView?.image = Image(url)
    }

    /**
     * Starts playing a sound with parameter
     * @param path - a sound path from resources
     */
    private fun playSound(path: String) {
        SoundPlayer.play(path)
    }

    /**
     * Stops player from current sound play
     */
    private fun stopCurrentSound(){
        SoundPlayer.stop()
    }

    override fun nativeKeyTyped(e: NativeKeyEvent) {}
    override fun nativeKeyPressed(e: NativeKeyEvent) {
        println("-------------------${e.keyCode}--------------------------")
        showAnimation(e.keyCode)
    }
    override fun nativeKeyReleased(e: NativeKeyEvent) {}
}