package console


enum class Direction(
    val key: Char,
    val heightDiff: Int,
    val widthDiff: Int,
) {
    UP('w', -1, 0),
    DOWN('s', 1, 0),
    LEFT('a', 0, -1),
    RIGHT('d', 0, 1);

    companion object {
        fun parse(value: Char): Direction? {
            val key = value.lowercase()[0]
            return Direction.entries.find { key == it.key }
        }
    }
}
