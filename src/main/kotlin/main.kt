fun main(args: Array<String>) {
    val filePath = args[0]
    val reader = FileReader()
    val lines = reader.readLines(filePath)

    val entrySource = EntryParser().parseLine(lines)

    println(entrySource)
}


data class EntrySource(val subList: List<String>) {

    init {
        require(subList.size == 3) { "only 3 lines" }
        subList.forEach {
            require(it.length == 27) { "only 27 " }
        }
    }


}

class EntryParser {

    fun parseLine(lines: List<String>): List<EntrySource> {
        return lines.chunked(4).filter { it.size == 4 } .map { EntrySource(it.subList(0, 3)) }

    }
}

