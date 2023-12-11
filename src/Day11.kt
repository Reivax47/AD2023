import kotlin.math.abs

fun main() {
    val output = mutableListOf<String>()
    data class Galaxie(val x: Int, val y: Int, val numero: Int)
    data class Distance(val un : Galaxie, val deux :Galaxie, var distance : Int )

    fun distanceManhattan(un: Galaxie, deux: Galaxie): Int {
        return abs(un.x - deux.x) + abs(un.y - deux.y)
    }

    fun part1(input: List<String>): Long {
        var reponse = 0L
        output.clear()
        val ciel = mutableListOf<Galaxie>()
        val lesDistance = mutableListOf<Distance>()
        input.forEach { it ->
            output.add(it)
            if (it.none { it == '#' }) {
                output.add(it)
                println("UN")
            }
        }
        var x = 0
        while (x < output[0].length) {
            var trouve = false
            for (y in output.indices) {
                if (output[y][x] == '#') {
                    trouve = true
                }
            }

            if (!trouve) {
                for (y in output.indices) {
                    output[y] = output[y].substring(0, x ) + '.' + output[y].substring(x )

                }
                println("DEUX")
                x++
            }
            x++
        }
        output.forEach {
            println(it)
        }

        var numero = 1
        for (y in output.indices) {
            for (posX in output[y].indices) {
                if (output[y][posX] == '#') {
                    ciel.add(Galaxie(posX,y,numero++))
                }

            }
        }
        ciel.forEach { uneGalaxie ->
            ciel.filter { it.numero > uneGalaxie.numero }.forEach { uneTrouvee ->
                lesDistance.add(Distance(uneGalaxie, uneTrouvee, -1))

            }
        }
        lesDistance.forEach { une ->
            une.distance = distanceManhattan(une.un, une.deux)

        }
        reponse = lesDistance.sumOf { it.distance.toLong() }
        return reponse
    }

    fun part2(input: List<String>): Int {
        var reponse = 0

        return reponse
    }

    // test if implementation meets criteria from the description, like:
    var testInput = readInput("Day11_test")
    check(part1(testInput) == 374L)

//    testInput = readInput("Day01_test2")
//    check(part2(testInput) == 281)

    val input = readInput("Day11")
    part1(input).println()
    part2(input).println()
}
