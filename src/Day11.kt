import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    data class Galaxie(val x: Int, val y: Int, val numero: Int)
    data class Distance(val un: Galaxie, val deux: Galaxie, var distance: Int)
    val colonneVide = mutableListOf<Int>()
    val ligneVide = mutableListOf<Int>()
    fun distanceManhattan(un: Galaxie, deux: Galaxie, facteur:Int): Int {
        val minX = min(un.x, deux.x)
        val maxX = max(un.x, deux.x)
        val minY = min(un.y, deux.y)
        val maxY = max(un.y, deux.y)
        val nbColVide = colonneVide.filter { it > minX && it <maxX }.size
        val nbLigneVide = ligneVide.filter { it > minY && it < maxY }.size
        var increment = 0
        if (nbLigneVide > 0) {
            increment += nbLigneVide * facteur - nbLigneVide
        }
        if (nbColVide > 0) {
            increment += nbColVide * facteur - nbColVide
        }
        return abs(un.x - deux.x) + abs(un.y - deux.y) + increment
    }

    fun part1(input: List<String>): Long {
        colonneVide.clear()
        ligneVide.clear()
        var reponse = 0L
        val ciel = mutableListOf<Galaxie>()
        val lesDistance = mutableListOf<Distance>()

        for (y in input.indices) {
            if (input[y].none { it == '#' }) {
                ligneVide.add(y)
            }
            println(input[y])
        }

        for (x in input[0].indices) {
            var trouve = false
            for (y in input.indices) {
                if (input[y][x] == '#') {
                    trouve = true
                }
            }
            if (!trouve) {
                colonneVide.add(x)
            }
        }

        var numero = 1
        for (y in input.indices) {
            for (posX in input[y].indices) {
                if (input[y][posX] == '#') {
                    ciel.add(Galaxie(posX, y, numero++))
                }

            }
        }
        ciel.forEach { uneGalaxie ->
            ciel.filter { it.numero > uneGalaxie.numero }.forEach { uneTrouvee ->
                lesDistance.add(Distance(uneGalaxie, uneTrouvee, -1))

            }
        }
        lesDistance.forEach { une ->
            une.distance = distanceManhattan(une.un, une.deux, 2)

        }
        reponse = lesDistance.sumOf { it.distance.toLong() }
        return reponse
    }

    fun part2(input: List<String>): Long {
        colonneVide.clear()
        ligneVide.clear()
        var reponse = 0L
        val ciel = mutableListOf<Galaxie>()
        val lesDistance = mutableListOf<Distance>()

        for (y in input.indices) {
            if (input[y].none { it == '#' }) {
                ligneVide.add(y)
            }
            println(input[y])
        }

        for (x in input[0].indices) {
            var trouve = false
            for (y in input.indices) {
                if (input[y][x] == '#') {
                    trouve = true
                }
            }
            if (!trouve) {
                colonneVide.add(x)
            }
        }

        var numero = 1
        for (y in input.indices) {
            for (posX in input[y].indices) {
                if (input[y][posX] == '#') {
                    ciel.add(Galaxie(posX, y, numero++))
                }

            }
        }
        ciel.forEach { uneGalaxie ->
            ciel.filter { it.numero > uneGalaxie.numero }.forEach { uneTrouvee ->
                lesDistance.add(Distance(uneGalaxie, uneTrouvee, -1))

            }
        }
        lesDistance.forEach { une ->
            une.distance = distanceManhattan(une.un, une.deux, 1000000)

        }
        reponse = lesDistance.sumOf { it.distance.toLong() }
        return reponse
    }

    // test if implementation meets criteria from the description, like:
    var testInput = readInput("Day11_test")
    check(part1(testInput) == 374L)

//    testInput = readInput("Day01_test2")
//    check(part2(testInput) == 8410L)

    val input = readInput("Day11")
    part1(input).println()
    part2(input).println()
}
