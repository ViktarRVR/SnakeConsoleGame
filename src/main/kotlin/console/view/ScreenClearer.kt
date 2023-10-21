package console.view

class ScreenClearer(osParam: OS) {

    enum class OS {
        WINDOWS, UNIX
    }

    private val screenCleanProcess: ProcessBuilder

    init {
        screenCleanProcess = when (osParam) {
            OS.WINDOWS -> ProcessBuilder("cmd", "/c", "cls").inheritIO()
            OS.UNIX -> ProcessBuilder("clear").inheritIO()
        }
    }

    fun clear() {
        screenCleanProcess.start().waitFor()
    }
}
