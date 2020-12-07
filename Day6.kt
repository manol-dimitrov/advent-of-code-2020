package day6

import day4.readFile
import java.nio.file.Files
import java.nio.file.Path

fun main() {
    val rawAnswers = readFile("day6.txt")

    val countAnyone = rawAnswers
        .split("\n\n").asSequence()
        .map { it.replace("\n", " ") }
        .map { it.split(" ") }
        .map { it.joinToString("") }
        .map { it.toSet().size }
        .sum()

    println(countAnyone)

    val countEveryone = rawAnswers
        .split("\n\n")
        .map { it.replace("\n", " ") }
        .map { it.split(" ") }
        .map { calculateCount(it) }
        .sum()

    println(countEveryone)
}

fun calculateCount(group: List<String>): Int {
    var counter = 0
    val groupSize = group.size

    if (groupSize == 1) counter += group[0].length

    if (groupSize > 1) {
        val map = getFrequencyMap(group)
        for (it in map.values.filter { it > 1 && it == groupSize }) {
            counter++
        }
    }

    return counter
}

fun getFrequencyMap(strings: List<String>): Map<Char, Int> {
    val allAnswers = strings.joinToString("")

    return getCharCountMap(allAnswers)
}

fun getCharCountMap(chars: String): Map<Char, Int> {
    val charCount = mutableMapOf<Char, Int>()

    for (c in chars) {
        charCount.putIfAbsent(c, 0)
        charCount[c] = charCount[c]!! + 1
    }

    return charCount
}

fun readFile(filePath: String): String {
    return Files.readString(Path.of(filePath))
}
