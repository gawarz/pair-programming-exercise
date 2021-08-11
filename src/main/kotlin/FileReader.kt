import java.io.File

class FileReader {
    fun readLines(filename: String): List<String> {
        val file = File(filename)
        return file.readLines()
    }
}
