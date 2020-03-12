import javafx.stage.Stage
import javafx.stage.StageStyle
import tornadofx.App
import tornadofx.launch
import kotlin.system.exitProcess


/**
 * Main app that extends from [App], it is the container of any views and user interaction
 */
class MyApp : App(MyView::class) {

    override fun start(stage: Stage) {
        //For borderless window
        //stage.initStyle(StageStyle.UNDECORATED)
        super.start(stage)
    }

    /**
     * Auxiliary function to launch [App]
     */
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch<MyApp>(args)
            exitProcess(0)
        }
    }

}