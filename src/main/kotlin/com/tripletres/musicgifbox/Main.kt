package com.tripletres.musicgifbox

import com.tripletres.musicgifbox.util.Arguments
import com.tripletres.musicgifbox.util.LogUtil

/**
 * Auxiliary class that launches [MyApp] app
 */
fun main(args: Array<String>) {
    readArgs(args)
    MyApp().main(args)
}

/**
 * Set arguments from program
 */
fun readArgs(args: Array<String>) {
    if(args.isNotEmpty()){
        //Look for file path
        Arguments.loaderPath = extractValueArg(args, "-f")
        //Look for default image
        Arguments.defaultImage = extractValueArg(args, "-d")
    }
}

/**
 * Extract flag value utility
 */
fun extractValueArg(args: Array<String>, flag: String): String?{
    args.indexOf(flag).let{
        if(it == -1)
            return@extractValueArg null

        try {
            val value = args[it + 1];
            return@extractValueArg value
        }catch (ex: NullPointerException){
            LogUtil.e("Main", "value param $flag not found", ex)
            return@extractValueArg null
        }
    }
}