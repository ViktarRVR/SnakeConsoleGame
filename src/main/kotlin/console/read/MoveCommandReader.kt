package console.read

import console.Direction
import java.io.BufferedReader
import java.io.Closeable
import java.io.InputStreamReader

class MoveCommandReader : Closeable {
    private val reader = BufferedReader(InputStreamReader(System.`in`))
    fun read(): Direction? {
        if (reader.ready()) {
            var line = reader.readLine()
            while (reader.ready()) {
                line = reader.readLine()
            }
            if (line.isNotEmpty()) {
                return Direction.parse(line.last())
            }
        }
        return null
    }

    override fun close() {
        reader.close()
    }
}
