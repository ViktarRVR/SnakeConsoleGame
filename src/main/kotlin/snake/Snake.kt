package snake

import console.Direction
import java.util.*

class Snake(
    var currentDirection: Direction,
    val body: LinkedList<Cell> = LinkedList(),
) {
    fun move(food: Cell?) {
        val head = body.first
        val newHead = head + currentDirection
        if (food != null) {
            newHead.visualRepresentation = food.visualRepresentation
            body.addFirst(newHead)
        } else {
            moveBody(newHead)
            body.addFirst(newHead)
            body.removeLast()
        }

    }

    fun head(): Cell {
        return body.first
    }

    fun tail(): Cell {
        return body.last
    }

    fun size(): Int {
        return body.size
    }

    private fun moveBody(newHead: Cell) {
        var current = newHead
        body.forEach {
            current.visualRepresentation = it.visualRepresentation
            current = it
        }
    }
}
