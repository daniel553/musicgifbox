package com.tripletres.musicgifbox.util

import java.io.BufferedReader

/**
 * Simple file reader
 */
object FileReader {

    /**
     * Reads a file from path. No error handled.
     * @param path a MUST valid path
     * @param separator string, space by default
     */
    fun readFile(path: String, separator: String = " "): String {
        var content = "";
        var bufferedReader: BufferedReader?  = null
        try {
            bufferedReader = javaClass.getResourceAsStream(path).bufferedReader()
            content = bufferedReader.readLines().joinToString(separator = separator)
        }catch (ex: Exception){
            LogUtil.e("FileReader", ex.localizedMessage, ex)
        }finally {
            bufferedReader?.close()
            return content
        }
    }

}