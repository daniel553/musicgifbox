import org.jnativehook.GlobalScreen
import org.jnativehook.NativeHookException
import org.jnativehook.keyboard.NativeKeyEvent
import org.jnativehook.keyboard.NativeKeyListener
import kotlin.system.exitProcess

/**
 * Global key listener, implements a global native hook that allows listening keyboard strokes
 */
object KeyListener : NativeKeyListener {
    init {
        try {
            GlobalScreen.registerNativeHook()
        } catch (ex: NativeHookException) {
            println("There was a problem registering the native hook.")
            println(ex.message)
            exitProcess(1)
        }
        //Uncomment this to make KeyListener listen
        //addNativeKeyListener(this)
    }

    /**
     * Adds a new [NativeKeyListener] object to global screen [GlobalScreen] registered.
     */
    fun addNativeKeyListener(listener: NativeKeyListener) {
        GlobalScreen.addNativeKeyListener(listener)
    }

    override fun nativeKeyTyped(e: NativeKeyEvent?) {}

    override fun nativeKeyPressed(e: NativeKeyEvent?) {}

    override fun nativeKeyReleased(e: NativeKeyEvent?) {}
}