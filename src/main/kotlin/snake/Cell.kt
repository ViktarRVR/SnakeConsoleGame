package snake

import console.Direction

class Cell(
    var visualRepresentation: Char,
    val height: Int,
    val width: Int,
) {
    operator fun plus(direction: Direction): Cell {
        return Cell(
            visualRepresentation,
            height + direction.heightDiff,
            width + direction.widthDiff
        )
    }

    fun samePosition(other: Cell?): Boolean {
        if (other == null) {
            return false
        }
        return height == other.height && width == other.width
    }
}
