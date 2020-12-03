package day2

import java.io.File

fun main() {
    val values = readFile("day2.txt")

    validate(values, "A")
    validate(values, "B")
}

private fun validate(values: List<String>, part: CharSequence) {
    var counter = 0

    for (value in values) {
        val line = value.split(" ").toTypedArray()

        val rangeStart = line[0].split("-")[0].toInt()
        val rangeEnd = line[0].split("-")[1].toInt()

        val char = line[1].subSequence(0, 1)[0]
        val password = line[2]

        when (part) {
            "A" -> {
                val occurrences = password.filter { it == char }.count()

                if (occurrences in rangeStart..rangeEnd) {
                    counter++
                }
            }
            "B" -> {
                val firstCondition = password.toCharArray()[rangeStart - 1] == char
                val secondCondition = password.toCharArray()[rangeEnd - 1] == char

                if ((firstCondition || secondCondition) && !(firstCondition && secondCondition)) {
                    counter++
                }
            }

        }
    }

    println(counter)
}

fun readFile(filePath: String): List<String> {
    return File(filePath).readLines()
}
