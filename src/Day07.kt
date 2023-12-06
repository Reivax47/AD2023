
fun main() {


    fun part1(input: List<String>): Int {

        var reponse = 0


        return reponse
    }


    fun part2(input: List<String>): Long {

        var reponse = 0L

        return reponse
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 288)

//    testInput = readInput("Day01_test2")
    check(part2(testInput) == 71503L)

    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()
}
