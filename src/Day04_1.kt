import kotlin.math.pow

fun main() {
    val lines = readInput("Day04_test")
    val sum = lines.map { line ->
        val raw = line.split(":")[1]
        val numbers = raw.split("|")
        val myNumbers = numbers[1].trim().split(" ").filter { it.isNotBlank() }.map { it.toInt() }
        val winningNumbers = numbers[0].trim().split(" ").filter { it.isNotBlank() }.map { it.toInt() }

        val intersection = myNumbers.intersect(winningNumbers.toSet())

        if (intersection.isNotEmpty())
            2.0.pow(intersection.size - 1)
        else 0
    }
    println(sum.sumOf { it.toInt() })
}