package day7

import java.io.File
import java.util.regex.Pattern

fun main() {
    val allRules = readFile("day7.txt")
    val mapOfBagColours = convert(allRules)

    val countA = mapOfBagColours.keys
        .filter { canHoldShinyGold(mapOfBagColours, it) }
        .count() - 1

    println(countA)

    val countB = totalBags(mapOfBagColours, "shiny gold") - 1

    println(countB)
}

fun canHoldShinyGold(rules: Map<String, Map<String, Int>>, bagColor: String): Boolean {
    val match = rules.getValue(bagColor)
        .keys
        .any { canHoldShinyGold(rules, it) }
    
    return bagColor == "shiny gold" || match
}

fun totalBags(rules: Map<String, Map<String, Int>>, bagColor: String): Int {
    return rules.getValue(bagColor)
        .entries
        .map { it.value * totalBags(rules, it.key) }
        .sum()
}

fun convert(lines: List<String>): Map<String, Map<String, Int>> {
    val map = mutableMapOf<String, MutableMap<String, Int>>()

    for (line in lines) {
        val colourRegex = Pattern.compile("^(.+) bags contain (.+)\\.$").matcher(line)

        if (colourRegex.find()) {
            val innerMap = map.computeIfAbsent(colourRegex.group(1)) { mutableMapOf() }

            val bagsHeld = colourRegex.group(2).split(", ")

            for (bagType in bagsHeld) {
                val bagTypeRegex = Pattern.compile("^(\\d+) (.+) bags?$").matcher(bagType)

                if (bagTypeRegex.find()) {
                    innerMap[bagTypeRegex.group(2)] = bagTypeRegex.group(1).toInt()
                }
            }
        }
    }

    return map
}

fun readFile(path: String): List<String> {
    return File(path).readLines()
}
