package com.tripletres.musicgifbox.datasource

import com.tripletres.musicgifbox.model.Clip
import com.tripletres.musicgifbox.util.Arguments
import com.tripletres.musicgifbox.util.FileReader
import com.tripletres.musicgifbox.util.LogUtil
import com.tripletres.musicgifbox.util.tag
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.io.File

/**
 * Clip Data source class. Fetches the data from a loader file and parse it to [Clip] objects.
 */
class ClipSource {
    /**
     * Previous loaded clips otherwise is populated from file
     */
    private val loadedClips = mutableListOf<Clip>()
    private lateinit var fileAux: File;

    /**
     * Gets a set of [Clip] from loaded clips, fetches from a file.
     */
    fun getClipData(): List<Clip> {
        try {
            if (loadedClips.isEmpty())
                loadedClips.addAll(parseJsonStringToClip(FileReader.readFile(
                    if(Arguments.loaderPath != null) Arguments.loaderPath!! else CLIP_DATA_FILE)))
            return loadedClips.toList()
        } catch (npe: NullPointerException) {
            LogUtil.e(tag, "WARNING: no data fetched from clip data file ${npe.message}", npe)
        }
        return emptyList()
    }

    /**
     * Parse elements from json string to a list of [Clip]
     * @return a set of clips or empty
     */
    private fun parseJsonStringToClip(jsonString: String): MutableList<Clip> {
        val mutableList = mutableListOf<Clip>()
        val json = Json(JsonConfiguration.Stable)
        try {
            // serializing objects
            json.parseJson(jsonString).jsonArray.forEach {
                val clip = json.parse(Clip.serializer(), it.jsonObject.toString())
                clip.sound = extractUri(clip.sound)
                clip.imageOrGif = extractUri(clip.imageOrGif)
                mutableList.add(clip)
            }
        } catch (e: Exception) {
            LogUtil.e(tag, "crazy things always happen to kotlinx ${e.message}", e)
        }
        return mutableList
    }

    private fun extractUri(path: String): String{
        fileAux = File(path)
        return fileAux.toURI().toString()
    }

    companion object {
        const val CLIP_DATA_FILE = "C:\\Users\\Daniel\\Desktop\\clipparts\\_loader.json"
    }
}