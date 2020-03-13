package com.tripletres.musicgifbox.view

import com.tripletres.musicgifbox.model.Clip
import com.tripletres.musicgifbox.util.KeyListener
import com.tripletres.musicgifbox.util.SoundPlayer
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
     * Collection of clips [Clip] to be loaded and presented in the screen.
     */
    private val clips: Array<Clip> = arrayOf(
        Clip(11, "mesi-goal.gif", "mesi-goal.mp3", 13000),
        Clip(83, "gio-gol.gif", "gio-gol.mp3", 13000),
        Clip(2, "helpme.gif", "helpme.mp3", 5000),
        Clip(3, "parrot-wtf.gif", "parrot-wtf.mp3", 4500),
        Clip(4, "jump-pussy.gif", "jump-pussy.mp3", 8000),
        Clip(5, "no-please.gif", "no-please.mp3", 8000),
        Clip(
            6,
            "estamos-en-la-b.gif",
            "estamos-en-la-b.mp3",
            10000
        ),
        Clip(7, "tom-scream.gif", "tom-scream.mp3", 3500),
        Clip(8, "cell-terror.gif", "cell-terror.mp3", 5000),
        Clip(9, "cherc.gif", "cherc.mp3", 4000),
        Clip(10, "yoloooo.gif", "yoloooo.mp3", 5000),
        Clip(3662, "ya-puedo-ver.gif", "ya-puedo-ver.mp3", 7200),
        Clip(3658, "rosa.gif", "rosa.mp3", 6000),
        Clip(3639, "se-mamo.gif", "se-mamo.mp3", 4000),
        Clip(53, "rata.gif", "rata.mp3", 6000),
        Clip(3663, "disfrazar.gif", "disfrazar.mp3", 5000),
        Clip(
            3665,
            "ah-no-bueno-memo.gif",
            "ah-no-bueno-memo.mp3",
            6000
        ),
        Clip(3667, "hermoso.gif", "hermoso.mp3", 6000)
    )

    /**
     * Root view, shows a default image at the beginning of times
     */
    override val root = vbox {
        val url = defaultImagePath
        mainImageView = imageview(url) {
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
        //Keycode must match with com.tripletres.musicgifbox.model.Clip keycode
        val clip = clips.find { it.keyCode == keyCode }

        if (clip != null) {
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
            replaceImage(clip.imageOfGif)
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