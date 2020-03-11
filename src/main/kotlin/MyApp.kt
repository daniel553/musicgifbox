import javafx.stage.Stage
import javafx.stage.StageStyle
import tornadofx.App
import tornadofx.launch


/**
 * Main app that extends fron [App], it is the cointainer of any views and user interaction
 */
class MyApp : App(MyView::class) {

    /**
     * Auxiliary function to launch [App]
     */
    fun main(args: Array<String>) {
        launch<MyApp>(args)
    }

    override fun start(stage: Stage) {
        //For borderless window
        stage.initStyle(StageStyle.UNDECORATED)
        super.start(stage)
    }

}