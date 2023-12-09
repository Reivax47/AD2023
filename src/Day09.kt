fun main() {
    fun part1(input: List<String>): Int {
        var reponse = 0
        input.forEach { ligne ->
            val historyRoot = ligne.split(" ").map { it.toInt() }
            val laListe = mutableListOf<MutableList<Int>>()
            var lastValue = 0

            laListe.add(historyRoot.toMutableList())

            var reduced = historyRoot.mapIndexed { index, i -> if (index < historyRoot.size -1) historyRoot[index +1 ] -i else -123456789 }.toMutableList()
            reduced.removeAt(reduced.size -1)
            laListe.add(reduced)
            while (reduced.any { it != 0 }) {
                reduced = reduced.mapIndexed { index, i -> if (index < reduced.size -1) reduced[index +1 ] -i else -123456789 }.toMutableList()
                reduced.removeAt(reduced.size -1)
                laListe.add(reduced)
            }

            laListe.reversed().forEach { serie ->
                lastValue += serie[serie.lastIndex]
            }
            reponse += lastValue
        }

        return reponse
    }

    fun part2(input: List<String>): Long {
        var reponse = 0L

        input.forEach { ligne ->
            val historyRoot = ligne.split(" ").map { it.toInt() }
            val laListe = mutableListOf<MutableList<Int>>()
            var lastValue = 0

            laListe.add(historyRoot.toMutableList())

            var reduced = historyRoot.mapIndexed { index, i -> if (index < historyRoot.size -1) historyRoot[index +1 ] -i else -123456789 }.toMutableList()
            reduced.removeAt(reduced.size -1)
            laListe.add(reduced)
            while (reduced.any { it != 0 }) {
                reduced = reduced.mapIndexed { index, i -> if (index < reduced.size -1) reduced[index +1 ] -i else -123456789 }.toMutableList()
                reduced.removeAt(reduced.size -1)
                laListe.add(reduced)
            }

            laListe.reversed().forEach { serie ->
                lastValue = serie[0] - lastValue
            }
            reponse += lastValue
        }
        return reponse
    }

    // test if implementation meets criteria from the description, like:
    var testInput = readInput("Day09_test")
    check(part1(testInput) == 114)

//    testInput = readInput("Day09_test2")
    check(part2(testInput) == 2L)

    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()
}
