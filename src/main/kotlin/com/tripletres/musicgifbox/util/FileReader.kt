package com.tripletres.musicgifbox.util

/**
 * Simple file reader
 */
object FileReader {

    /**
     * Reads a file from path. No error handled.
     * @param path a MUST valid path
     * @param separator string, space by default
     */
    fun readFile(path: String, separator: String = " "): String =
        javaClass.getResourceAsStream(path).bufferedReader().readLines().joinToString(separator = separator)

}