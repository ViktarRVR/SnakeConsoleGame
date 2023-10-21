import console.read.MoveCommandReader
import console.view.Screen
import snake.Board

private const val DEFAULT_SIZE = 10
private const val MIN_SIZE = 3

fun main(args: Array<String>) {
    val argsMap = args.toList()
        .chunked(2)
        .associate { it[0] to if (it.size == 2) it[1] else null }
    val screen = Screen(argsMap["-console"])

    val height = parseBoardDimension(argsMap["-h"])
    val width = parseBoardDimension(argsMap["-w"])
    println(height)
    println(width)


    val board = Board(height, width)
    screen.draw(board.toString())
    MoveCommandReader().use { moveCommandReader ->
        while (!board.isEndGame) {
            Thread.sleep(1000)
            board.newSnakeDirection(moveCommandReader.read())
            board.makeMovement()
            screen.draw(board.toString())
        }
    }
}

fun parseBoardDimension(
    argValue: String?
): Int {
    val value = argValue?.toIntOrNull() ?: DEFAULT_SIZE
    return if (MIN_SIZE > value) {
        DEFAULT_SIZE
    } else {
        value
    }
}
