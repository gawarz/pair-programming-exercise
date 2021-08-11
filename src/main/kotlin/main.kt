fun main(args: Array<String>) {
    val filePath = args[0]
    val reader = FileReader()
    val lines = reader.readLines(filePath)

    val entrySource = EntryParser().parseLine(lines)

    entrySource.forEach { source ->
        val digits = source.getDigits()
        println(digits)
    }
}


data class EntrySource(val subList: List<String>) {

    init {
        require(subList.size == 3) { "only 3 lines" }
        subList.forEach {
            require(it.length == 27) { "only 27 " }
        }
    }

    fun getDigits(): List<Digit> {
        val charList = mutableListOf<Digit>()
        val list: List<List<String>> = subList.map {
            it.chunked(3)
        }

        val list1 = list[0]
        val list2 = list[1]
        val list3 = list[2]

        val digits = list1.zip(list2) { a, b ->
            a + b
        }.zip(list3) { a, b -> a + b }

        return digits
            .map { it.toCharArray().toList() }
            .map { Digit(it) }
    }

}

data class Digit(
    val charList: List<Char>
)

class EntryParser {

    fun parseLine(lines: List<String>): List<EntrySource> {
        return lines.chunked(4).filter { it.size == 4 }.map { EntrySource(it.subList(0, 3)) }

    }
}

