fun main() {
    val testInput = readInput("Day01_test")

//    part1(testInput)
    part2(testInput)
}

private fun part2(testInput: List<String>) {
    val sum = testInput.sumOf { input ->

        val firstDigitIndex = input.indexOfFirst { it.isDigit() }

        var first = input.firstOrNull { it.isDigit() }?.digitToInt() ?: 0
        var mamad = Int.MAX_VALUE
        NumberText.values().forEach { numberText ->
            if (input.firstSubStringIndexOf(numberText.name) < firstDigitIndex && input.firstSubStringIndexOf(numberText.name) < mamad) {
                first = numberText.number()
                mamad = input.firstSubStringIndexOf(numberText.name)
            }
        }


        val lastDigitIndex = input.indexOfLast { it.isDigit() }

        var last = input.lastOrNull { it.isDigit() }?.digitToInt() ?: 0
        var mamad2 = Int.MIN_VALUE
        NumberText.values().forEach { numberText ->
            if (input.lastSubStringIndexOf(numberText.name) > lastDigitIndex && input.lastSubStringIndexOf(numberText.name) > mamad2) {
                last = numberText.number()
                mamad2 = input.lastSubStringIndexOf(numberText.name)
            }
        }

        println("$input -> $first & $last")

        first * 10 + last
    }
    println(sum)
}

fun part1(testInput: List<String>) {
    val sum = testInput.sumOf {
        val first = it.first { it.isDigit() }.digitToInt()
        val last = it.last { it.isDigit() }.digitToInt()
        first * 10 + last
    }
    println(sum)
}

enum class NumberText {
    One {
        override fun number() = 1
    },
    Two {
        override fun number() = 2
    },
    Three {
        override fun number() = 3
    },
    Four {
        override fun number() = 4
    },
    Five {
        override fun number() = 5
    },
    Six {
        override fun number() = 6
    },
    Seven {
        override fun number() = 7
    },
    Eight {
        override fun number() = 8
    },
    Nine {
        override fun number() = 9
    };

    abstract fun number(): Int
}


fun ignoreCaseOpt(ignoreCase: Boolean) =
    if (ignoreCase) setOf(RegexOption.IGNORE_CASE) else emptySet()


fun String.lastSubStringIndexOf(pat: String, ignoreCase: Boolean = true) =
    subStringIndexOf(pat, ignoreCase).lastOrNull() ?: Int.MIN_VALUE

fun String.firstSubStringIndexOf(pat: String, ignoreCase: Boolean = true) =
    subStringIndexOf(pat, ignoreCase).firstOrNull() ?: Int.MAX_VALUE

fun String.subStringIndexOf(pat: String, ignoreCase: Boolean = true): List<Int> =
    pat.toRegex(ignoreCaseOpt(ignoreCase))
        .findAll(this)
        .map { it.range.first }
        .toList()