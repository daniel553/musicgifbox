package com.tripletres.musicgifbox.controller

import com.tripletres.musicgifbox.datasource.ClipSource
import com.tripletres.musicgifbox.model.Clip

/**
 * Class Clip controller, from gathering data and return it to the views
 */
class ClipController {

    /**
     * Gets the clip data from data source
     * @return a set of clips
     */
    fun clipData(): List<Clip> {
        return ClipSource().getClipData()
    }

}