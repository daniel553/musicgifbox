package com.tripletres.musicgifbox

import com.tripletres.musicgifbox.view.MyView
import javafx.stage.Stage
import javafx.stage.StageStyle
import tornadofx.App
import tornadofx.launch
import kotlin.system.exitProcess


/**
 * Main app that extends from [App], it is the container of any views and user interaction
 */
class MyApp : App(MyView::class) {

    /**
     * Auxiliary function to launch [App]
     */
    fun main(args: Array<String>) {
        launch<MyApp>(args)
        exitProcess(0)
    }

    override fun start(stage: Stage) {
        //For borderless window
        //stage.initStyle(StageStyle.UNDECORATED)
        stage.initStyle(StageStyle.UNDECORATED)
        super.start(stage)
    }
}