package com.tripletres.musicgifbox.util

/**
 * Log and Error handler
 */
object LogUtil {

    fun i(msg: String) {
        println(msg);
    }
    fun d(t: String, msg: String) {
        println("$t --- $msg")
    }
    fun e(t: String, msg: String, err: Throwable?) {
        println("$t --- $msg : $err")
    }
}