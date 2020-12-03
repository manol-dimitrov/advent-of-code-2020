package day3

import java.io.File

fun main() {
    val grid = readFile("day3.txt")

    //part A
    traverse(grid, 3, 1)

    //part B
    traverse(grid, 1, 1)
    traverse(grid, 3, 1)
    traverse(grid, 5, 1)
    traverse(grid, 7, 1)
    traverse(grid, 1, 2)
}

fun traverse(grid: ArrayList<String>, right: Int, down: Int) {
    var treeCount = 0
    var x = 0
    var y = 0

    while (y < grid.size) {
        val modx = x % grid[0].length
        val cursor = grid[y][modx].toString()

        if (cursor == "#") treeCount++

        x += right
        y += down
    }

    println(treeCount)
}

fun readFile(filePath: String): ArrayList<String> {
    val lines = arrayListOf<String>()

    File(filePath).forEachLine { lines.add(it) }

    return lines
}
