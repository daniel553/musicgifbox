package com.tripletres.musicgifbox.model

import kotlinx.serialization.Serializable


/**
 * com.tripletres.musicgifbox.model.Clip part data object
 * @constructor
 * @param keyCode - numeric value of keyboard keycode, eg 0,1,2
 * @param imageOrGif - file resource path of an image or gif, eg "image.png"
 * @param sound - file resource path of a sound clip, eg "sound.mp3"
 * @param timeout - [0..N] positive long for seconds to time out.
 */
@Serializable
data class Clip(val keyCode: Int, val imageOrGif: String, val sound: String, val timeout: Long = 0)