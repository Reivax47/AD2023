fun main() {
    fun part1(input: List<String>): Int {
        var reponse = 0

        input.forEach { uneLigne ->
            val ligneFiltree = uneLigne.filter { it.isDigit() }.map { it.toString().toInt() }
            reponse += ligneFiltree[0] * 10 + ligneFiltree[ligneFiltree.size - 1]
        }

        return reponse
    }

    fun part2(input: List<String>): Int {
        var reponse = 0
        val lesChiffresTochiffres = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9

        )
        val lesChiffres = arrayListOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

        input.forEach { uneLigne ->
            val ligneFiltree = mutableListOf<Int>()
            for (i in uneLigne.indices) {

                val leChar = uneLigne[i]
                if (leChar.isDigit()) {
                    ligneFiltree.add(leChar.toString().toInt())
                } else {
                    lesChiffresTochiffres[lesChiffres.firstOrNull() {
                        i == uneLigne.indexOf(
                            it,
                            i
                        )
                    }]?.let { ligneFiltree.add(it) }
                }
            }

            reponse += ligneFiltree[0] * 10 + ligneFiltree[ligneFiltree.size - 1]
        }
        return reponse
    }

    // test if implementation meets criteria from the description, like:
    var testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    testInput = readInput("Day01_test2")
    check(part2(testInput) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
