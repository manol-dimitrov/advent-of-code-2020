package day5

import java.io.File

fun main() {
    val passes = readFile("day5.txt")

    val seatIds = passes.map { toBinaryNumber(it) }.toIntArray().sortedArray()

    //part A
    println(seatIds.max())

    //part B
    findMySeat(seatIds)
}

fun findMySeat(seatIds: IntArray) {
    for (i in 0 until seatIds.size - 1) {
        val current = seatIds[i]
        val next = seatIds[i + 1]

        if (next - current > 1) {
            println("My seat ID is: " + (current + 1))
        }
    }
}

fun toBinaryNumber(pass: String): Int {
    val binary = pass
        .replace("F", "0")
        .replace("B", "1")
        .replace("R", "1")
        .replace("L", "0")

    return binary.toInt(2)
}

fun readFile(filePath: String): List<String> {
    return File(filePath).readLines()
}
