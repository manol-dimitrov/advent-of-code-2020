package day4

import java.nio.file.Files
import java.nio.file.Path
import java.util.regex.Pattern

fun main() {
    val rawPassports = readFile("day4.txt")
    
    val formattedPassports = rawPassports
        .split("\n\n")
        .map { it.replace("\n", " ") }
        .map { it.split(" ") }
        .toList()

    //part A
    println(formattedPassports.count { containsAllFields(it) })

    //part B
    println(formattedPassports.count { containsAllFields(it) && allFieldsAreValid(it) })
}

fun containsAllFields(passport: List<String>): Boolean {
    val allLabels = passport.map { it.split(":")[0] }.toSet()
    return allLabels.containsAll(getConditions().keys)
}

fun allFieldsAreValid(passport: List<String>): Boolean {
    return passport
        .map { it.split(":") }
        .all { validateFields(it[0], it[1]) }
}

fun validateFields(key: String, condition: String): Boolean {
    if (!getConditions().containsKey(key)) return true

    val pattern = Pattern.compile(getConditions()[key])

    return pattern.matcher(condition).matches()
}

fun readFile(filePath: String): String {
    return Files.readString(Path.of(filePath))
}

private fun getConditions(): Map<String, String> {
    return mapOf(
        "byr" to "^(200[0-2]|19[2-9][0-9])$",
        "iyr" to "^(2020|201[0-9])$",
        "eyr" to "^(2030|202[0-9])$",
        "hgt" to "^((1([5-8][0-9]|9[0-3])cm)|((59|6[0-9]|7[0-6])in))$",
        "hcl" to "^(#[0-9a-f]{6})$",
        "ecl" to "^(amb|blu|brn|gry|grn|hzl|oth)$",
        "pid" to "^[0-9]{9}$"
    )
}
