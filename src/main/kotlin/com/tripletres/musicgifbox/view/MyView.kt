package com.tripletres.musicgifbox.view

import com.tripletres.musicgifbox.controller.ClipController
import com.tripletres.musicgifbox.model.Clip
import com.tripletres.musicgifbox.util.KeyListener
import com.tripletres.musicgifbox.util.SoundPlayer
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.paint.Color
import javafx.stage.StageStyle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jnativehook.keyboard.NativeKeyEvent
import org.jnativehook.keyboard.NativeKeyListener
import tornadofx.*
import kotlin.system.exitProcess

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
    private val defaultImagePath = "petepandalogo-borderless.png"

    /**
     * Collection of clips [Clip] to be loaded and presented in the screen.
     */
    private val clips = ClipController().clipData()

    private var xOffset = 0.0
    private var yOffset = 0.0

    /**
     * Root view, shows a default image at the beginning of times
     */
    override val root = stackpane {
        val url = defaultImagePath
        setPrefSize(400.0, 400.0)

        mainImageView = imageview(url) {
            fitHeightProperty().bind(parent.prefHeight(400.0).toProperty())
            fitWidthProperty().bind(parent.prefWidth(400.0).toProperty())
        }

        setOnMousePressed {
            xOffset = it.sceneX
            yOffset = it.sceneY
        }

        setOnMouseDragged {
            currentStage?.x = it.screenX - xOffset
            currentStage?.y = it.screenY - yOffset
        }

    }

    override fun onDock() {
        super.onDock()
        //Manual fill of root scene, am I doing ok?
        root.scene.fill = Color.TRANSPARENT
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
            playSound(clip.sound).also {
                if(clip.timeout > 0){
                    //Create a time out.
                    GlobalScope.launch {
                        delay(clip.timeout)
                        //Reset default image
                        replaceImage(defaultImagePath)
                        stopCurrentSound()
                    }
                }
            }
            replaceImage(clip.imageOrGif)

        } else {
            println("Cannot play clip")
        }
    }

    /**
     * Replaces the main image view with parameter
     * @param url - a new image path from resources
     */
    private fun replaceImage(url: String) {
        try {
            mainImageView?.image = Image(url)
        } catch (iae: IllegalArgumentException) {
            println(iae.message)
        }
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
        if(e.keyCode == 1) exit()

        println("-------------------${e.keyCode}--------------------------")
        showAnimation(e.keyCode)
    }
    override fun nativeKeyReleased(e: NativeKeyEvent) {}

    /**
     * Exits process when current window is focus
     */
    private fun exit() {
        if(currentWindow != null && currentWindow!!.isFocused)
            exitProcess(0)
    }

}