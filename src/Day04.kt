import kotlin.math.pow

fun main() {


    fun part1(input: List<String>): Int {

        var reponse = 0
        input.forEach { uneLigne ->
            val game = uneLigne.split(" | ")
            val winning = game[0].substringAfter(": ").trim().split(" ").map { it.trim() }
            val tirage = game[1].split(" ").map { it.trim() }.filter { it != "" }
            val result = winning.intersect(tirage).size
            if (result > 0) {
                reponse += (2.0.pow(result - 1)).toInt()
            }
        }
        return reponse
    }


    fun part2(input: List<String>): Long {

        var reponse = 0L
        val mesCards = mutableListOf<Card>()
        input.forEach { uneLigne ->
            val game = uneLigne.split(" | ")
            val num = game[0].substringAfter("Card ").split(":")[0].trim().toInt()
            val winning = game[0].substringAfter(": ").trim().split(" ").map { it.trim() }
            val tirage = game[1].split(" ").map { it.trim() }.filter { it != "" }
            mesCards.add(Card(num, winning.intersect(tirage).size, 1))

        }

        mesCards.forEach { cardActuelle ->
            if (cardActuelle.result > 0) {
                for (y in 1..cardActuelle.result) {
                    val cardToadd = mesCards.find { it.num == cardActuelle.num + y }
                    cardToadd?.nb = cardToadd?.nb?.plus(cardActuelle.nb)!!
                }
            }
        }
        reponse = mesCards.sumOf { it.nb.toLong() }
        return reponse
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)

//    testInput = readInput("Day01_test2")
    check(part2(testInput) == 30L)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}

data class Card(val num: Int, val result: Int, var nb: Int)