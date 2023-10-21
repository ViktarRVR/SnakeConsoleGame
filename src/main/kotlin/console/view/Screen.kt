package console.view

class Screen(
    osParam: String?,
) {
    private val screenClearer: ScreenClearer

    init {
        screenClearer = ScreenClearer(osValueOf(osParam) ?: ScreenClearer.OS.UNIX)
    }

    fun draw(value: String) {
        screenClearer.clear()
        println(value)
    }

    private fun osValueOf(value: String?): ScreenClearer.OS? {
        if (value == null) {
            return null
        }
        val lowerCaseValue = value.lowercase()
        return ScreenClearer.OS.entries.find { it.name.lowercase() == lowerCaseValue }
    }
}
