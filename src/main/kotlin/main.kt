import java.lang.IllegalStateException

fun main(args: Array<String>) {
    val filePath = args[0]
    val reader = FileReader()
    val lines = reader.readLines(filePath)

    val entrySource = EntryParser().parseLine(lines)

    entrySource.forEach { source ->
        val accountNumber = source.getLong()
        println(accountNumber)
    }
}

data class EntrySource(val subList: List<String>) {

    init {
        require(subList.size == 3) { "only 3 lines" }
        subList.forEach {
            require(it.length == 27) { "only 27 " }
        }
    }

    val map: Map<String,String> = mapOf(
        "     |  |" to "1",
        " _  _||_ " to "2",
        " _  _| _|" to "3",
        "   |_|  |" to "4",
        " _ |_  _|" to "5",
        " _ |_ |_|" to "6",
        " _   |  |" to "7",
        " _ |_||_|" to "8",
        " _ |_| _|" to "9",
        " _ | ||_|" to "0"
    )
    
    private fun getDigits(): List<String> {
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
    }

     fun getLong(): Long =
         getDigits().joinToString(separator = "", prefix = "") {
             map[it] ?: throw IllegalStateException("unknown mapping for: '$it'")
         }.toLong()
    

}


class EntryParser {

    fun parseLine(lines: List<String>): List<EntrySource> {
        return lines.chunked(4).filter { it.size == 4 }.map { EntrySource(it.subList(0, 3)) }

    }
}

