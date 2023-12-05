fun main() {
    val testInput = readInput("Day02_test")

    part1(testInput)
}

fun part1(testInput: List<String>) {
    val sum = testInput.sumOf { input ->
        val gameNumber = input.substringBefore(":").filter { it.isDigit() }.toInt()
        val value = input.substringAfter(":")
        val shouldCount = value.split(";").all { game ->
            println(game)
            game.split(",").all { record ->
                val count = record.filter { it.isDigit() }.toInt()
                val color = CubeColor.entries.first {
                    record.contains(it.name, ignoreCase = true)
                }
                println("$record ${count < color.maxCount()}")
                count <= color.maxCount()
            }
        }

        println("$shouldCount")
        println("*****")

        if (shouldCount)
            gameNumber
        else
            0
    }
    println(sum)
}

enum class CubeColor {
    Red {
        override fun maxCount() = 12
    },
    Green {
        override fun maxCount() = 13
    },
    Blue {
        override fun maxCount() = 14
    };


    abstract fun maxCount(): Int
}