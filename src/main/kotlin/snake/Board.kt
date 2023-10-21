package snake

import console.Direction
import java.util.*
import kotlin.random.Random

private const val EMPTY_CELL = ' '
private val FOOD_ALLOWED_CHARS = ('0'..'9') + ('a'..'z')

class Board(
    private val height: Int,
    private val width: Int,
) {
    private val board: Array<Array<Char>> = Array(height) {
        Array(width) {
            EMPTY_CELL
        }
    }
    private val snake = Snake(
        Direction.DOWN, LinkedList(
            listOf(
                Cell('1', 2, width / 2),
                Cell('2', 1, width / 2),
                Cell('3', 0, width / 2)
            )
        )
    )
    private var food: Cell?

    init {
        snake.body.forEach {
            board[it.height][it.width] = it.visualRepresentation
        }
        food = createFood()
        redrawBoard()
    }

    var isEndGame = false
        private set

    fun newSnakeDirection(direction: Direction?) {
        direction?.also {
            snake.currentDirection = it
        }
    }

    fun makeMovement() {
        val newHead = snake.head() + snake.currentDirection

        if (newHead.height < 0
            || newHead.height >= height
            || newHead.width < 0
            || newHead.width >= width
            || (board[newHead.height][newHead.width] != EMPTY_CELL
                    && !newHead.samePosition(food)
                    && !newHead.samePosition(snake.tail()))
        ) {
            isEndGame = true
        } else {
            val foodForSnake = if (newHead.samePosition(food)) food else null
            snake.move(foodForSnake)
            if (foodForSnake != null) {
                food = createFood()
            }
            redrawBoard()
        }
    }

    private fun createFood(): Cell? {
        val numberEmptyCells = height * width - snake.size()
        if (numberEmptyCells == 0) {
            return null
        }
        val foodNum = Random.nextInt(numberEmptyCells)

        var emptyCellNumber = -1
        for (h in 0..<height) {
            for (w in 0..<width) {
                if (board[h][w] == EMPTY_CELL) {
                    emptyCellNumber++
                }
                if (emptyCellNumber == foodNum) {
                    return Cell(FOOD_ALLOWED_CHARS[Random.nextInt(FOOD_ALLOWED_CHARS.size)], h, w)
                }
            }
        }
        throw IllegalStateException("Cannot create food")
    }

    private fun redrawBoard() {
        for (h in 0..<height) {
            for (w in 0..<width) {
                board[h][w] = EMPTY_CELL
            }
        }

        snake.body.forEach {
            board[it.height][it.width] = it.visualRepresentation
        }

        food?.run {
            board[height][width] = visualRepresentation
        }
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        if (isEndGame) {
            return "GAME OVER"
        }

        stringBuilder.addHeaderFooter()
        board.forEach {
            stringBuilder.append("||")
            stringBuilder.append(it.joinToString(separator = "."))
            stringBuilder.append("||")
            stringBuilder.append("\n")
        }
        stringBuilder.addHeaderFooter()
        return stringBuilder.toString()
    }

    private fun StringBuilder.addHeaderFooter() {
        append("||")
        repeat(width) {
            append("=|")
        }
        append("|")
        append("\n")
    }
}
