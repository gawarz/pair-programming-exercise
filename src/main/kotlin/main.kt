fun main(args: Array<String>) {
    val filePath = args[0]
    val reader = FileReader()
    val lines = reader.readLines(filePath)
    println(lines.toString())
}
