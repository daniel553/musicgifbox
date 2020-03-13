/**
 * Auxiliary class that launches [MyApp] app
 */
open class AppLauncher {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            MyApp().main(args)
        }
    }
}