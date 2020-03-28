package com.tripletres.musicgifbox.util

import javafx.scene.image.Image

/**
 * Image loader helper, loads images from a path, and it saves to variable to dispose.
 */
object ImageLoader {
    private val images = hashMapOf<String, Image>()

    /**
     * Tries to load a new image from a path or load it from hashed images
     */
    @Throws(NullPointerException::class)
    fun loadImage(path: String): Image {
        if (path.isNotEmpty()) {
            if (!images.containsKey(path))
                images[path] = Image(path)

            return images[path]!! //why?
        }

        throw NullPointerException("Image can't be loaded")
    }
}