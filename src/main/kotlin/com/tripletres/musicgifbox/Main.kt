package com.tripletres.musicgifbox

import com.tripletres.musicgifbox.util.Arguments
import com.tripletres.musicgifbox.util.LogUtil

/**
 * Auxiliary class that launches [MyApp] app
 */
fun main(args: Array<String>) {
    if(args.isNotEmpty()){
        //Look for file path
        args.indexOf("-f").let{
            try {
                Arguments.loaderPath = args[it + 1];
            }catch (ex: NullPointerException){
                LogUtil.e("Main", "file param not found", ex)
            }
        }
    }
    MyApp().main(args)
}