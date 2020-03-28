package com.tripletres.musicgifbox.util

import org.jnativehook.GlobalScreen
import org.jnativehook.NativeHookException
import org.jnativehook.keyboard.NativeKeyListener
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger
import kotlin.system.exitProcess

/**
 * Global key listener, implements a global native hook that allows listening keyboard strokes
 */
object KeyListener{
    init {
        try {
            clearLoggingFromJNativeHook()
            GlobalScreen.registerNativeHook()
        } catch (ex: NativeHookException) {
            LogUtil.e("KeyListener","There was a problem registering the native hook.", ex)
            exitProcess(1)
        }
    }

    /**
     * Adds a new [NativeKeyListener] object to global screen [GlobalScreen] registered.
     */
    fun addNativeKeyListener(listener: NativeKeyListener) {
        GlobalScreen.addNativeKeyListener(listener)
    }

    /**
     * Sets level off dor logging from JNativeHook
     */
    private fun clearLoggingFromJNativeHook(){
        LogManager.getLogManager().reset();
        Logger.getLogger(GlobalScreen::class.java.packageName).level = Level.OFF
    }
}