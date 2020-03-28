package com.tripletres.musicgifbox.util

/**
 * To get the canonical name of class as a tag to log.
 */
val Any.tag: String
    get() {
        return this::class.java.canonicalName
    }