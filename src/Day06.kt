import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sqrt

fun main() {


    fun part1(input: List<String>): Int {

        var reponse = 1
        val times = input[0].substringAfter("Time: ").split(" ").filter { it != "" }.map { it.toInt() }
        val distances = input[1].substringAfter("Distance: ").split(" ").filter { it != "" }.map { it.toInt() }

        for (race in times.indices) {
            val time = times[race]
            val distance = distances[race]
            var nbWin = 0
            for (letemps in 1 until time) {
                if ((time - letemps) * letemps > distance) {
                    nbWin++
                }
            }
            reponse *= nbWin
        }
        return reponse
    }


    fun part2(input: List<String>): Long {

        val times = input[0].substringAfter("Time: ").replace(" ", "").toDouble()
        val distances = input[1].substringAfter("Distance: ").replace(" ", "").toDouble()
        val delta = times.pow(2.0).toLong() - 4 * distances
        val laRacineDelta = sqrt(delta)

        val x1 = floor((times * -1 - laRacineDelta) / -2).toLong()
        val x2 = floor((-1 * times + laRacineDelta) / -2).toLong()
        return x1 - x2
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 288)

//    testInput = readInput("Day01_test2")
    check(part2(testInput) == 71503L)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
